package sk.stopangin.movement;

//definuje sposob pohybu (napr sikmo, rovno, do L...)
public interface MovementType {
    Coordinates apply(); //aplikovanim movement typu dostanem nove coordinates (evetualne miesto noveho umiestnenia)
}
