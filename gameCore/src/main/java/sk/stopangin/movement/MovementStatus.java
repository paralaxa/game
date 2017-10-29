package sk.stopangin.movement;

public enum MovementStatus {
    DONE(true),
    COLLISION(false),
    NON_EXISTING_FIELD(false),
    INVALID_POSITION(false),
    ACTION_POSSIBLE(true),
    ACTION_REQUIRED(false);

    private boolean allowMovement;

    MovementStatus(boolean allowMovement) {
        this.allowMovement = allowMovement;
    }

    public boolean isAllowMovement() {
        return allowMovement;
    }

    public void setAllowMovement(boolean allowMovement) {
        this.allowMovement = allowMovement;
    }
}
