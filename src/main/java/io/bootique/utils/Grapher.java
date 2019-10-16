package io.bootique.utils;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.grapher.graphviz.GraphvizGrapher;
import com.google.inject.grapher.graphviz.GraphvizModule;
import io.bootique.BQRuntime;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Grapher {
    public static void printGraph(BQRuntime bqRuntime) throws IOException {
        Injector injector = Guice.createInjector(new GraphvizModule());
        GraphvizGrapher grapher = injector.getInstance(GraphvizGrapher.class);
        PrintWriter out = new PrintWriter(new File("modules.dot"), "UTF-8");
        grapher.setOut(out);
        grapher.setRankdir("TB");
        grapher.graph(bqRuntime.getInstance(Injector.class));
    }
}
