
package etudiant;

import java.util.Random;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Main {
    
    public static void main (String[] args){
        Random random = new Random();
        Scanner scanner =new Scanner(System.in);
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
        
       
        while (selected.isAlive() || opponent.isAlive()){ 
            
            if (opponent.isAlive()){
                if (random.nextInt(100)>10 && random.nextInt(100)<30 ){
                    mage.heal();
                }
                else if (random.nextInt(100)< 10)
                    opponent.use (selected);
                else
                    mage.attack(warrior);
            }
            else{
                System.out.printf("%s won", warrior.getName());
                break;
            }
            
        }   
        
        
        
    }
}
