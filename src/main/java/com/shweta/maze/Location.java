package com.shweta.maze;

public class Location {

    private int row;
    private int column;
    private LocationState state;

    public Location(int row, int column, char sign) {
        this.row = row;
        this.column = column;
        this.state = LocationState.getValue(String.valueOf(sign));
    }

    public Location(int row, int column, LocationState state) {
        this.row = row;
        this.column = column;
        this.state = state;
    }

    public boolean isOpen() {
        return state == LocationState.OPEN ||
                state == LocationState.START ||
                state == LocationState.EXIT;
    }

    public boolean isStart() {
        return state == LocationState.START;
    }

    public boolean isExit() {
        return state == LocationState.EXIT;
    }

    public boolean isWalled() {
        return state == LocationState.WALLED;
    }
   
    public boolean isValidRepresentation() {
        return isOpen() || isWalled();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public LocationState getState() {
        return state;
    }

    public String toString() {
        return "(" + row + ", " + column + ", " + state + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (column != location.column) return false;
        if (row != location.row) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hasCode = row;
        hasCode = 31 * hasCode + column;
        hasCode = 31 * hasCode + (state != null ? state.hashCode() : 0);
        return hasCode;
    }
}