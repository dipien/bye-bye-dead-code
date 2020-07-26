package com.github.konifar.gradle.remover.util

class ResultsReport {

    private static List<String> results = new ArrayList<>()

    static void clearResults() {
        results.clear()
    }

    static void addResult(String result) {
       results.add(result)
    }

    static List<String> getResults() {
        return results
    }
}