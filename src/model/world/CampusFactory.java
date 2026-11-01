package model.world;

import model.entity.Player;
import model.inventory.*;

public class CampusFactory {

    public static Campus createEnsimag(Player player) {
        Campus campus = new Campus();

        Building batimentA = new Building("Bâtiment A", 10, 10, false);
        Building batimentE = new Building("Bâtiment E", 10, 10, true);
        Street rueDeLaPiscine = new Street("Rue de la Piscine", 20, 5);

        batimentA.connectTo(rueDeLaPiscine);
        batimentE.connectTo(rueDeLaPiscine);

        // Objets dans le Bâtiment A
        batimentA.getCellAt(2, 3).addItem(new Key("Badge associatif", "Une carte étudiante trouvée par terre...", batimentE));
        batimentA.getCellAt(5, 5).addItem(new Food("Sandwich", "Un sandwich du CROUS", 30));
        batimentA.getCellAt(7, 1).addItem(new Weapon("Regle", "Une règle en métal", 5));

        // Objets dans le Bâtiment E
        batimentE.getCellAt(1, 1).addItem(new Armor("Veste labo", "Une blouse de laboratoire", 10));
        batimentE.getCellAt(4, 6).addItem(new PowerUp("Cafe", "Un cafe bien serré", "Vitesse +1"));
        batimentE.getCellAt(8, 2).addItem(new Food("Pomme", "Une pomme bien rouge", 15));

        // Objets dans la Rue de la Piscine
        rueDeLaPiscine.getCellAt(10, 1).addItem(new Weapon("Parapluie", "Un parapluie solide", 3));
        rueDeLaPiscine.getCellAt(15, 3).addItem(new PowerUp("Boisson énergisante", "Ca donne des ailes", "Endurance +2"));

        campus.addZone(batimentA);
        campus.addZone(batimentE);
        campus.addZone(rueDeLaPiscine);

        player.setPosition(new Position(rueDeLaPiscine, rueDeLaPiscine.getWidth() / 2, rueDeLaPiscine.getHeight() / 2));

        return campus;
    }
}
