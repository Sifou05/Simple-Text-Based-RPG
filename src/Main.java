import java.util.Scanner;

class Player 
{
    String name, loadout;
    int hp;
    int ap; // Attack power
    int sp, bp; // Sword power, bow power
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
            this.hp = 90;
            this.ap = 10;
        }
    }


    void attack(Goblin goblin) 
    {
        System.out.println(name + " attacked the goblin!");
        goblin.gob_hp -= ap;
        System.out.println("Goblin now has: " + goblin.gob_hp + " hp!");
    }

    void attack(Skeleton skeleton) 
    {
        System.out.println(name + " attacked the skeleton!");
        skeleton.skel_hp -= ap;
        System.out.println("Skeleton now has: " + skeleton.skel_hp + " hp!");
    }
}
    
class Goblin
{
    int gob_hp = 45;
    int gob_dmg = 25;
}

class Skeleton
{
    int skel_hp = 40;
    int skel_dmg = 30;
}

class Main
{
    public static void main(String[] args)
    {
    	int counter = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Choose your loadout (e.g., Sword, Bow, Staff): ");
        String loadout = scanner.nextLine(); 
        Player player = new Player(name, loadout); 
        Goblin goblin = new Goblin();
        System.out.println("A wild goblin has appeared!");
        while (player.hp > 0 && goblin.gob_hp > 0)
        {
        	System.out.println("What would you like to do?: ");
        	System.out.println("1. Attack!");
        	System.out.println("2. Flee!");
        	String input = scanner.nextLine();
        	
        	if (input.equals("1"))
        	{
        		player.attack(goblin);
        		if (goblin.gob_hp > 0)
        		{
        			player.hp -= goblin.gob_dmg;
        			System.out.println("The goblin attacked back!");
        			System.out.println("Player" + player.name + "now has" + player.hp + "!");
        		}
        	}else if (input.equals("2"))
        	{
        		System.out.println("You run away like a pussy!");
        		break;
        	}else
        	{
        		System.out.println("Invalid input! Try again!");
        		counter = counter + 1;
        	}if (counter == 3)
        	{
        		System.out.println("Are you stupid? TRY AGAIN!");
        	}
        }
        
        if (player.hp <= 0)
        {
        	System.out.println("You died! How did you manage that?");
        }else if (goblin.gob_hp <= 0)
        {
        	System.out.println("The goblin was defeated!");
        }
        scanner.close();
    }
}