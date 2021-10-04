package org.example;

import io.javalin.Javalin;

public class App {
    public static void main( String[] args ) {
        Javalin javalin = Javalin.create().start(3052);
        javalin.get("/", ctx ->{
            System.out.println(ctx.status());
            ctx.json("Write something on URL (/pif/:a/:gipotenyza(katet)/:(katet)/) ||||||||||||||||||||||||" +
                    " if a = 1 -> nado nayti gipotenyzy |||||||||||||||||||||||||||||||" +
                    " if a = 2 -> nado nayti katet |||||||||||||||||||||||||||||" +
                    " esli nado naiti katet, pervim chislom pishite gipotenyzy");
        });
        javalin.get("/pif/:a/:b/:c/", ctx ->{
            System.out.println(ctx.status());
            int a = Integer.parseInt(ctx.pathParam("a"));
            double b = Double.parseDouble(ctx.pathParam("b"));
            double c = Double.parseDouble(ctx.pathParam("c"));
            if (a < 0 | b < 0 | c < 0) {
                ctx.json("her tam");
            }else if (a == 1){
                ctx.json(Math.sqrt((b*b)+(c*c)));
            }else if (a == 2) {
                if (b < c) {
                    ctx.json("Gipotenyza < kateta");
                } else {
                    ctx.json(Math.sqrt((b * b) - (c * c)));
                }
            }else {
                ctx.status(404);
            }
        });
    }
}
