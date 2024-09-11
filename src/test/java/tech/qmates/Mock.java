package tech.qmates;

import java.util.ArrayList;

class Mock {

    private ArrayList<String> calls = new ArrayList<>(5);

    public void registerCall(String attack) {
        this.calls.add(attack);
    }

    public int calls() {
        return calls.size();
    }

    public boolean called(String takeDamage) {
        return calls.contains(takeDamage);
    }
}
