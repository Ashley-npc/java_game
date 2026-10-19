
package etudiant;

import java.util.Random;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Main {
    
    
    public static void playerTurn(Character selected,Character opponent){
        Scanner scanner =new Scanner(System.in);
        System.out.println("Choose your action");
        System.out.println("1. Attack");
        System.out.println("2. Heal");
        System.out.println("3. Heavy attack(usable only once)");
        
        int choice = scanner.nextInt();
        
        switch (choice){
            case 1 -> {selected.attack(opponent);break;}
            case 2 -> {selected.heal();break;}
            case 3 -> {if(selected instanceof SpecialAbility ability) ability.use(opponent);break;}
            
            default -> {System.out.println("Invalid choice");break;}
        } 
        
    }
    
    public static void main (String[] args){
        Scanner scanner =new Scanner(System.in);
        Random random = new Random();
        
        Map<String, Character> characters = new HashMap<>();
        
        
        
        String name1 = "knight";int vie1 = 100;int Damage1 = 20;
        String name2 = "mage";int vie2 = 100; int Damage2 = 20;
        String name3 = "goblin";int vie3 = 60; int Damage3 = 15;
        
        Character warrior = new Warrior(name1,vie1,Damage1);
        Character mage = new Mage(name2,vie2,Damage2);
        Character monster = new Monster(name3,vie3,Damage3);
        
        characters.put("knight",warrior);
        characters.put("mage", mage);
        characters.put("goblin",monster);
        
        
        System.out.println("Choose a character\n");
        for (String name : characters.keySet()){
            System.out.println(name);
        }
        String choice1 = scanner.nextLine();
        
        
        System.out.println("Choose an oppoenent");
        for (String name : characters.keySet()){
            if (!name.equals(choice1))
                System.out.println(name);
        }
        String choice2 = scanner.nextLine();
        
        Character opponent = characters.get(choice2);
        Character selected = characters.get(choice1);
        
        System.out.println("you deliver the first strike");
        
        while (selected.isAlive() && opponent.isAlive()){ 
            
            playerTurn(selected,opponent);
            
            if (opponent.isAlive()){
                if (random.nextInt(100)>10 && random.nextInt(100)<30 ){
                    mage.heal();
                }
                else if (random.nextInt(100)< 10)
                    if (opponent instanceof SpecialAbility ability){
                        ability.use(selected);
                    }
                    
                else
                    opponent.attack(selected);
            }
            else{
                System.out.printf("%s won", warrior.getName());
                break;
            }
            
        }   
        
        
        
    }
}
