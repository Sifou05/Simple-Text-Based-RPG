import java.util.Scanner;
import java.util.Random;

class Player {
    String name, loadout;
    int hp;
    int ap;
    int upgrades;

    public Player(String name, String loadout) {
        this.name = name;
        this.loadout = loadout;
        this.upgrades = 0;

        if (loadout.equalsIgnoreCase("Sword")) {
            this.hp = 120;
            this.ap = 20;
        } else if (loadout.equalsIgnoreCase("Bow")) {
            this.hp = 100;
            this.ap = 25;
        } else if (loadout.equalsIgnoreCase("Staff")) {
            this.hp = 80;
            this.ap = 30;
        } else {
            // Fallback shouldn't happen due to input validation
            this.hp = 90;
            this.ap = 10;
        }
    }

    void attack(Goblin goblin) {
        System.out.println(name + " attacked the Goblin!");
        goblin.gob_hp -= ap;
        System.out.println("Goblin now has: " + goblin.gob_hp + " hp!");
    }

    void attack(Skeleton skeleton) {
        System.out.println(name + " attacked the Skeleton!");
        skeleton.skel_hp -= ap;
        System.out.println("Skeleton now has: " + skeleton.skel_hp + " hp!");
    }

    void attack(Ghoul ghoul) {
        System.out.println(name + " attacked the Ghoul!");
        ghoul.ghoul_hp -= ap;
        System.out.println("Ghoul now has: " + ghoul.ghoul_hp + " hp!");
    }

    void attack(Slime slime) {
        System.out.println(name + " attacked the Slime!");
        slime.slime_hp -= ap;
        System.out.println("Slime now has: " + slime.slime_hp + " hp!");
    }

    void attack(Haunted_rusty_blade hrb) {
        System.out.println(name + " attacked the Haunted Rusty Blade!");
        hrb.hrb_hp -= ap;
        System.out.println("Haunted Rusty Blade now has: " + hrb.hrb_hp + " hp!");
    }

    void attack(Big_Benjamin bb) {
        System.out.println(name + " attacked Big Benjamin!");
        bb.bb_hp -= ap;
        System.out.println("Big Benjamin now has: " + bb.bb_hp + " hp!");
    }
}

class Goblin {
    int gob_hp = 45;
    int gob_dmg = 25;
}

class Skeleton {
    int skel_hp = 40;
    int skel_dmg = 30;
}

class Ghoul {
    int ghoul_hp = 50;
    int ghoul_dmg = 20;
}

class Slime {
    int slime_hp = 20;
    int ghoul_dmg = 15;
}

class Haunted_rusty_blade {
    int hrb_hp = 5;
    int ghoul_dmg = 99;
}

class Big_Benjamin {
    int bb_hp = 150;
    int bb_dmg = 45;
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        String loadout;
        while (true) {
            System.out.println("Choose your loadout (Sword, Bow, Staff): ");
            loadout = scanner.nextLine();

            if (loadout.equalsIgnoreCase("Sword") ||
                loadout.equalsIgnoreCase("Bow") ||
                loadout.equalsIgnoreCase("Staff")) {
                break;
            } else {
                System.out.println("Invalid loadout! Please choose Sword, Bow, or Staff.");
            }
        }

        Player player = new Player(name, loadout);
        System.out.println("Welcome, " + player.name + "! Loadout: " + player.loadout + " | HP: " + player.hp + " | AP: " + player.ap);

        int encounter = rand.nextInt(6); // Random enemy

        switch (encounter) {
            case 0 -> {
                Goblin goblin = new Goblin();
                System.out.println("\nA wild Goblin appears!");
                handleCombat(scanner, player, goblin, goblin.gob_dmg, goblin.gob_hp, "Goblin");
            }
            case 1 -> {
                Skeleton skeleton = new Skeleton();
                System.out.println("\nA scary Skeleton emerges!");
                handleCombat(scanner, player, skeleton, skeleton.skel_dmg, skeleton.skel_hp, "Skeleton");
            }
            case 2 -> {
                Ghoul ghoul = new Ghoul();
                System.out.println("\nA sneaky Ghoul appears!");
                handleCombat(scanner, player, ghoul, ghoul.ghoul_dmg, ghoul.ghoul_hp, "Ghoul");
            }
            case 3 -> {
                Slime slime = new Slime();
                System.out.println("\nA squishy Slime wiggles in!");
                handleCombat(scanner, player, slime, slime.ghoul_dmg, slime.slime_hp, "Slime");
            }
            case 4 -> {
                Haunted_rusty_blade hrb = new Haunted_rusty_blade();
                System.out.println("\nA cursed Haunted Rusty Blade floats toward you!");
                handleCombat(scanner, player, hrb, hrb.ghoul_dmg, hrb.hrb_hp, "Haunted Rusty Blade");
            }
            case 5 -> {
                Big_Benjamin bb = new Big_Benjamin();
                System.out.println("\nBig Benjamin towers over the battlefield!");
                handleCombat(scanner, player, bb, bb.bb_dmg, bb.bb_hp, "Big Benjamin");
            }
        }

        scanner.close();
    }

    public static void handleCombat(Scanner scanner, Player player, Object enemy, int enemy_dmg, int enemy_hp, String enemyName) {
        int counter = 0;

        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Attack!");
            System.out.println("2. Flee!");

            String input = scanner.nextLine();

            if (input.equals("1")) {
                switch (enemyName) {
                    case "Goblin" -> player.attack((Goblin) enemy);
                    case "Skeleton" -> player.attack((Skeleton) enemy);
                    case "Ghoul" -> player.attack((Ghoul) enemy);
                    case "Slime" -> player.attack((Slime) enemy);
                    case "Haunted Rusty Blade" -> player.attack((Haunted_rusty_blade) enemy);
                    case "Big Benjamin" -> player.attack((Big_Benjamin) enemy);
                }

                int currentHp = switch (enemyName) {
                    case "Goblin" -> ((Goblin) enemy).gob_hp;
                    case "Skeleton" -> ((Skeleton) enemy).skel_hp;
                    case "Ghoul" -> ((Ghoul) enemy).ghoul_hp;
                    case "Slime" -> ((Slime) enemy).slime_hp;
                    case "Haunted Rusty Blade" -> ((Haunted_rusty_blade) enemy).hrb_hp;
                    case "Big Benjamin" -> ((Big_Benjamin) enemy).bb_hp;
                    default -> 0;
                };

                if (currentHp > 0) {
                    player.hp -= enemy_dmg;
                    System.out.println("The " + enemyName + " attacked back!");
                    System.out.println("Player " + player.name + " now has " + player.hp + " HP!");
                } else {
                    System.out.println("You defeated the " + enemyName + "!");
                    break;
                }

            } else if (input.equals("2")) {
                System.out.println("You fled the battle!");
                break;
            } else {
                System.out.println("Invalid input! Try again.");
                counter++;
                if (counter == 3) {
                    System.out.println("Are you even trying?");
                }
            }

            if (player.hp <= 0) {
                System.out.println("You died! Game over.");
                break;
            }
        }
    }
}
