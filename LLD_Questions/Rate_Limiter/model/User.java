package LLD_Questions.Rate_Limiter.model;

import LLD_Questions.Rate_Limiter.enums.UserType;
// import lombok.AllArgsConstructor;
// import lombok.Getter;

// @Getter
// @AllArgsConstructor
public class User {
    private final String userId;
    private final UserType tier;
    public User(String userId, UserType tier) {
        this.userId = userId;
        this.tier = tier;
    }
    public String getUserId() {
        return userId;
    }
    public UserType getTier() {
        return tier;
    }

    
}
