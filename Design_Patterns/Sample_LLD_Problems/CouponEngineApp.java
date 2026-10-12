package Design_Patterns.Sample_LLD_Problems;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Save as CouponEngineApp.java and run:
// javac CouponEngineApp.java && java CouponEngineApp

public class CouponEngineApp {

    enum CouponType { SEASONAL, LOYALTY, BANKING, BULK }
    enum ApplicabilityType { CART, PRODUCT }
    enum StrategyType { FLAT, PERCENT, PERCENT_WITH_CAP }

    // ---------- Core entities ----------

    static final class Product {
        private final String id;
        private final String name;
        private final String category;
        private final double price;

        Product(String id, String name, String category, double price) {
            if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
            this.id = id;
            this.name = name;
            this.category = category;
            this.price = price;
        }

        String getId() { return id; }
        String getName() { return name; }
        String getCategory() { return category; }
        double getPrice() { return price; }
    }

    static final class CartItem {
        private final Product product;
        private int quantity;

        CartItem(Product product, int quantity) {
            if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
            this.product = product;
            this.quantity = quantity;
        }

        Product getProduct() { return product; }
        int getQuantity() { return quantity; }
        double getPrice() { return product.getPrice() * quantity; }
    }

    static final class Cart {
        private final List<CartItem> items = new ArrayList<>();
        private final List<Coupon> appliedCoupons = new ArrayList<>();
        private final boolean loyaltyMember;
        private double finalTotal;

        Cart(boolean loyaltyMember) {
            this.loyaltyMember = loyaltyMember;
        }

        synchronized void addProduct(Product product, int quantity) {
            items.add(new CartItem(product, quantity));
            recalculateTotal();
        }

        synchronized boolean isLoyaltyMember() { return loyaltyMember; }

        synchronized double getOriginalTotal() {
            return items.stream().mapToDouble(CartItem::getPrice).sum();
        }

        synchronized double getFinalTotal() { return finalTotal; }

        synchronized List<CartItem> getItemsSnapshot() {
            return Collections.unmodifiableList(new ArrayList<>(items));
        }

        synchronized List<Coupon> getAppliedCouponsSnapshot() {
            return Collections.unmodifiableList(new ArrayList<>(appliedCoupons));
        }

        // Call only while holding this Cart's monitor.
        synchronized boolean hasAppliedCoupon(String couponId) {
            return appliedCoupons.stream().anyMatch(c -> c.getId().equals(couponId));
        }

        // Atomically updates the total and records the coupon.
        synchronized boolean applyCouponAtomically(Coupon coupon, double discount) {
            if (hasAppliedCoupon(coupon.getId())) return false;
            if (discount <= 0 || finalTotal <= 0) return false;

            finalTotal = roundMoney(Math.max(0, finalTotal - Math.min(discount, finalTotal)));
            appliedCoupons.add(coupon);
            return true;
        }

        private void recalculateTotal() {
            finalTotal = getOriginalTotal();
        }
    }

    // ---------- Discount strategies ----------

    interface DiscountStrategy {
        double calculate(double amount);
    }

    static final class FlatDiscountStrategy implements DiscountStrategy {
        private final double discountAmount;

        FlatDiscountStrategy(double discountAmount) {
            this.discountAmount = Math.max(0, discountAmount);
        }

        public double calculate(double amount) {
            return Math.min(Math.max(0, amount), discountAmount);
        }
    }

    static final class PercentDiscountStrategy implements DiscountStrategy {
        private final double percent;

        PercentDiscountStrategy(double percent) {
            if (percent < 0 || percent > 100) throw new IllegalArgumentException("Percent must be 0..100");
            this.percent = percent;
        }

        public double calculate(double amount) {
            return Math.max(0, amount) * percent / 100.0;
        }
    }

    static final class PercentWithCapDiscountStrategy implements DiscountStrategy {
        private final double percent;
        private final double maxDiscount;

        PercentWithCapDiscountStrategy(double percent, double maxDiscount) {
            if (percent < 0 || percent > 100 || maxDiscount < 0)
                throw new IllegalArgumentException("Invalid percentage or cap");
            this.percent = percent;
            this.maxDiscount = maxDiscount;
        }

        public double calculate(double amount) {
            return Math.min(Math.max(0, amount) * percent / 100.0, maxDiscount);
        }
    }

    static final class DiscountStrategyFactory {
        DiscountStrategy create(StrategyType type, double value, double cap) {
            switch (type) {
                case FLAT: return new FlatDiscountStrategy(value);
                case PERCENT: return new PercentDiscountStrategy(value);
                case PERCENT_WITH_CAP: return new PercentWithCapDiscountStrategy(value, cap);
                default: throw new IllegalArgumentException("Unsupported strategy: " + type);
            }
        }
    }

    // ---------- Coupons ----------

    interface Coupon {
        String getId();
        CouponType getType();
        ApplicabilityType getApplicabilityType();
        boolean isApplicable(Cart cart);
        boolean isCombinable(Coupon other);
        double getDiscount(Cart cart);
        Set<String> getIncompatibleCouponIds();
    }

    static abstract class BaseCoupon implements Coupon {
        private final String id;
        private final CouponType type;
        private final ApplicabilityType applicabilityType;
        private final DiscountStrategy strategy;
        private final Set<String> incompatibleCouponIds;

        BaseCoupon(String id, CouponType type, ApplicabilityType applicabilityType,
                   DiscountStrategy strategy, Set<String> incompatibleCouponIds) {
            this.id = Objects.requireNonNull(id);
            this.type = Objects.requireNonNull(type);
            this.applicabilityType = Objects.requireNonNull(applicabilityType);
            this.strategy = Objects.requireNonNull(strategy);
            this.incompatibleCouponIds = new HashSet<>(incompatibleCouponIds);
        }

        public String getId() { return id; }
        public CouponType getType() { return type; }
        public ApplicabilityType getApplicabilityType() { return applicabilityType; }

        public boolean isCombinable(Coupon other) {
            return !incompatibleCouponIds.contains(other.getId())
                    && !other.getIncompatibleCouponIds().contains(id);
        }

        @Override
        public Set<String> getIncompatibleCouponIds() {
            return Collections.unmodifiableSet(incompatibleCouponIds);
        }

        protected double eligibleAmount(Cart cart) {
            if (applicabilityType == ApplicabilityType.CART) return cart.getFinalTotal();
            return cart.getItemsSnapshot().stream()
                    .filter(item -> isProductEligible(item.getProduct()))
                    .mapToDouble(CartItem::getPrice)
                    .sum();
        }

        protected boolean isProductEligible(Product product) { return true; }

        protected double calculateEligibleDiscount(Cart cart) {
            return strategy.calculate(eligibleAmount(cart));
        }

        public double getDiscount(Cart cart) {
            return calculateEligibleDiscount(cart);
        }
    }

    static final class SeasonalOffer extends BaseCoupon {
        private final String eligibleCategory;

        SeasonalOffer(String id, DiscountStrategy strategy, String eligibleCategory,
                      ApplicabilityType applicability, Set<String> incompatibleIds) {
            super(id, CouponType.SEASONAL, applicability, strategy, incompatibleIds);
            this.eligibleCategory = eligibleCategory;
        }

        public boolean isApplicable(Cart cart) {
            return cart.getFinalTotal() > 0
                    && cart.getItemsSnapshot().stream().anyMatch(i ->
                    eligibleCategory == null || eligibleCategory.equals(i.getProduct().getCategory()));
        }

        protected boolean isProductEligible(Product product) {
            return eligibleCategory == null || eligibleCategory.equals(product.getCategory());
        }
    }

    static final class LoyaltyDiscount extends BaseCoupon {
        LoyaltyDiscount(String id, DiscountStrategy strategy, ApplicabilityType applicability,
                        Set<String> incompatibleIds) {
            super(id, CouponType.LOYALTY, applicability, strategy, incompatibleIds);
        }

        public boolean isApplicable(Cart cart) {
            return cart.isLoyaltyMember() && cart.getFinalTotal() > 0;
        }
    }

    static final class BankCoupon extends BaseCoupon {
        private final String requiredBank;
        private final double minimumSpend;

        BankCoupon(String id, DiscountStrategy strategy, ApplicabilityType applicability,
                   String requiredBank, double minimumSpend, Set<String> incompatibleIds) {
            super(id, CouponType.BANKING, applicability, strategy, incompatibleIds);
            this.requiredBank = requiredBank;
            this.minimumSpend = minimumSpend;
        }

        // Payment/bank selection is not part of the current Cart model; the bank is
        // retained as coupon metadata and can be checked when payment details are added.
        public boolean isApplicable(Cart cart) {
            return cart.getFinalTotal() >= minimumSpend;
        }

        String getRequiredBank() { return requiredBank; }
    }

    static final class BulkPurchaseDiscount extends BaseCoupon {
        private final double minimumSpend;

        BulkPurchaseDiscount(String id, DiscountStrategy strategy, ApplicabilityType applicability,
                             double minimumSpend, Set<String> incompatibleIds) {
            super(id, CouponType.BULK, applicability, strategy, incompatibleIds);
            this.minimumSpend = minimumSpend;
        }

        public boolean isApplicable(Cart cart) {
            return cart.getFinalTotal() >= minimumSpend;
        }
    }

    // ---------- Coupon manager ----------

    static final class CouponManager {
        private final Map<String, Coupon> coupons = new ConcurrentHashMap<>();

        void registerCoupon(Coupon coupon) {
            Coupon previous = coupons.putIfAbsent(coupon.getId(), coupon);
            if (previous != null) throw new IllegalArgumentException("Coupon ID already exists: " + coupon.getId());
        }

        Coupon getCoupon(String couponId) {
            Coupon coupon = coupons.get(couponId);
            if (coupon == null) throw new IllegalArgumentException("Unknown coupon: " + couponId);
            return coupon;
        }

        boolean applyCoupon(Cart cart, String couponId) {
            Objects.requireNonNull(cart);
            Coupon selected = getCoupon(couponId);

            // Serialize coupon applications to the same cart. Different carts can
            // still be processed concurrently.
            synchronized (cart) {
                if (cart.hasAppliedCoupon(selected.getId())) return false;
                if (!selected.isApplicable(cart)) return false;

                for (Coupon applied : cart.getAppliedCouponsSnapshot()) {
                    if (!selected.isCombinable(applied)) return false;
                }

                double discount = selected.getDiscount(cart);
                return cart.applyCouponAtomically(selected, discount);
            }
        }

        List<Coupon> getApplicableCoupons(Cart cart) {
            List<Coupon> result = new ArrayList<>();
            for (Coupon coupon : coupons.values()) {
                if (coupon.isApplicable(cart)) result.add(coupon);
            }
            return result;
        }
    }

    private static double roundMoney(double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }

    public static void main(String[] args) {
        DiscountStrategyFactory strategyFactory = new DiscountStrategyFactory();
        CouponManager couponManager = new CouponManager();

        Product headphones = new Product("P1", "Headphones", "Electronics", 5000);
        Product book = new Product("P2", "Book", "Books", 1000);

        Cart cart = new Cart(true);
        cart.addProduct(headphones, 1);
        cart.addProduct(book, 2);

        couponManager.registerCoupon(new SeasonalOffer(
                "SUMMER20",
                strategyFactory.create(StrategyType.PERCENT_WITH_CAP, 20, 1000),
                "Electronics",
                ApplicabilityType.PRODUCT,
                Set.of("BANK500")));

        couponManager.registerCoupon(new BankCoupon(
                "BANK500",
                strategyFactory.create(StrategyType.FLAT, 500, 0),
                ApplicabilityType.CART,
                "ExampleBank",
                3000,
                Set.of("SUMMER20")));

        couponManager.registerCoupon(new LoyaltyDiscount(
                "LOYALTY10",
                strategyFactory.create(StrategyType.PERCENT, 10, 0),
                ApplicabilityType.CART,
                Set.of()));

        System.out.printf("Original total: %.2f%n", cart.getOriginalTotal());
        System.out.println("SUMMER20 applied: " + couponManager.applyCoupon(cart, "SUMMER20"));
        System.out.println("BANK500 applied: " + couponManager.applyCoupon(cart, "BANK500")
                + " (incompatible with SUMMER20)");
        System.out.println("LOYALTY10 applied: " + couponManager.applyCoupon(cart, "LOYALTY10"));
        System.out.printf("Final total: %.2f%n", cart.getFinalTotal());
        System.out.println("Applied coupons: " + cart.getAppliedCouponsSnapshot().stream()
                .map(Coupon::getId).toList());
    }
}
