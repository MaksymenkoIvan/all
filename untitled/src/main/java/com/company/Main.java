package com.company;

import io.javalin.Javalin;

public class Main {

    public static void main(String[] args) {
        Javalin javalin = Javalin.create().start(2023);
        javalin.get("/", ctx -> {
            System.out.println(ctx.status());
        });
    }
}
