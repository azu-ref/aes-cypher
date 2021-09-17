import cypher.domain.MasterKey;
import cypher.domain.Params;
import cypher.domain.ToCypher;

import static spark.Spark.*;

import java.util.function.Supplier;
import  java.util.logging.Logger;
import com.google.gson.Gson;
import cypher.domain.ToDecipher;

public class Main {
    private static MasterKey secretKey = new MasterKey();
    private static Gson g = new Gson();


    private static final long serialVersionUID=1L;
    public static Logger logger=Logger.getLogger("global");

    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");

        get("/hello/:name", (req, res) -> {
            return  "Hello " + req.params(":name");
        });

        before("/*", (req, res) -> {
            String body = req.body();
            Params params = g.fromJson(body,  Params.class);
            //ToDecipher decipher = g.fromJson(body,  ToDecipher.class);

            req.attribute("params", params);
            //req.attribute("decipher", decipher);
        });

        post("/cipher", (req, res) -> {
            Params params = req.attribute("params");

            logger.info(params.phone);

            ToCypher cipher = new ToCypher(params.phone, secretKey.setSecretKey());
            return g.toJson(cipher.encrypt());
        });

        post("/decipher", (req, res) -> {
            Params params = req.attribute("params");

            logger.info(params.phone);

            ToDecipher cipher = new ToDecipher(params.phone, params.destinationId, secretKey.setSecretKey());
            return g.toJson(cipher.decrypt());
        });
    }
}