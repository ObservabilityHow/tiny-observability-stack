package how.observability;

import io.javalin.Javalin;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.prometheusmetrics.PrometheusConfig;
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TinyApp {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(TinyApp.class);

        PrometheusMeterRegistry prometheus = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
        Metrics.addRegistry(prometheus);

        Counter hits = Counter.builder("tiny_hits_total")
                .description("Total hello hits")
                .register(prometheus);

        Javalin app = Javalin.create()
                .get("/hello", ctx -> {
                    hits.increment();
                    log.info("Handled /hello for {}", ctx.ip());
                    ctx.result("Hello " + ctx.ip());
                })
                .get("/metrics", ctx -> ctx.result(prometheus.scrape()))
                .start(8080);

        log.info("Tiny app started at http://localhost:8080");
    }
}
