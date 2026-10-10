package Design_Patterns.Structural_Patterns.Adapter;

// 1. Target interface expected by the client
interface Reports {
    public String getJsonData(String data);
}

// 2. Adaptee: Provides XML data from a raw input
class XMLDataProvider {
    // Expect data in "name:id" format (e.g. "Alice:42")
    String getXmlData(String data) {
        int sep = data.indexOf(':');
        String name = data.substring(0, sep);
        String id   = data.substring(sep + 1);
        // Build an XML representation
        return "<user>"
                + "<name>" + name + "</name>"
                + "<id>"   + id   + "</id>"
                + "</user>";
    }
}

// 3. Adapter: Implemetns Reports by converting XML -> JSON
class XMLDataProviderAdapter implements Reports {

    private XMLDataProvider xmlDataProvider;

    public XMLDataProviderAdapter(XMLDataProvider xmlDataProvider) {
        this.xmlDataProvider = xmlDataProvider;
    }

    @Override
    public String getJsonData(String data) {
        // 1. Get XML from the adaptee
        String xml = xmlDataProvider.getXmlData(data);

        // 2. Naïvely parse out <name> and <id> values
        int startName = xml.indexOf("<name>") + 6;
        int endName   = xml.indexOf("</name>");
        String name   = xml.substring(startName, endName);

        int startId = xml.indexOf("<id>") + 4;
        int endId   = xml.indexOf("</id>");
        String id    = xml.substring(startId, endId);

        // 3. Build and return JSON
        return "{\"name\":\"" + name + "\", \"id\":" + id + "}";
    }

}

// 4. Client code works only with Reports interface
class ClientCode {
    public void getReport(Reports report, String rawData) {
        System.out.println("Processed JSON: "
            + report.getJsonData(rawData));
    }
}

public class JsonReport {
    public static void main(String[] args) {
        // 1. Create the adaptee
        XMLDataProvider xmlProv = new XMLDataProvider();

        // 2. Make our adapter
        Reports adapter = new XMLDataProviderAdapter(xmlProv);

        // 3. Give it some raw data
        String rawData = "Alice:42";

        // 4. Client prints the JSON
        ClientCode client = new ClientCode();

        client.getReport(adapter, rawData);
        // → Processed JSON: {"name":"Alice", "id":42}
    }   
}