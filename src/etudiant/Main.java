
package etudiant;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class Main {
    
    public static List<Character> stock(){
        
        String name1 = "knight";int vie1 = 100;int Damage1 = 20;
        String name2 = "mage";int vie2 = 100; int Damage2 = 20;
        String name3 = "goblin";int vie3 = 60; int Damage3 = 15;
        String name4 = "elf";int vie4 = 100; int Damage4 = 15;
        
        Character warrior = new Warrior(name1,vie1,Damage1);
        Character mage = new Mage(name2,vie2,Damage2);
        Character monster = new Monster(name3,vie3,Damage3);
        Character elf = new Elf (name4,vie4,Damage4);
        
        ArrayList<Character> party = new ArrayList<>();
        party.add(warrior);
        party.add(mage);
        party.add(monster);
        party.add(elf);
        
        return party;
    }
    
    public static Character choose(List<Character> party){
        Scanner scanner = new Scanner(System.in);
        
        int i;
        Character persoChoisi = null;
        do{
            i=0;
            System.out.println("Choose a character");
            System.out.println("------------------");
            for (Character perso : party){
                System.out.println("-"+perso.getName()+"  "+ perso.getVie()+"hp  "+ perso.getDamage()+"DMG");
            
            }
            
            String choice1 = scanner.nextLine();
            for (Character perso : party){
                if (perso.getName().equals(choice1)){
                   if (!perso.isAlive()){
                       System.out.printf("%s is dead, choose another\n", perso.getName() );
                       break;
                   }
                    System.out.println("you have chosen "+perso.getName()+" "+ perso.getVie()+"hp  "+ perso.getDamage()+"DMG");
                    persoChoisi = perso;
                    break;
                }
                i++;
            }
            
            if (i == party.size()) System.out.println("->Invalid choice");
        }while(i == party.size());
        return persoChoisi;
    }
    
    
    public static void playerTurn(Character selected,Character opponent){
        int choice;
        do{
            
            Scanner scanner =new Scanner(System.in);
            System.out.println("Choose your action");
            System.out.println("1. Attack");
            System.out.println("2. Heal");
            System.out.println("3. Heavy attack(usable only once)");
            System.out.println("4. Switch character");
        
        
            choice = scanner.nextInt();
            switch (choice){
                case 1 -> {selected.attack(opponent);break;}
                case 2 -> {selected.heal();break;}
                case 3 -> {if(selected instanceof SpecialAbility ability) ability.use(opponent);break;}
                case 4 -> {selected = choose(stock());break;}
            
                default -> {System.out.println("Invalid choice");break;}
            }
            
        }while (0>choice || choice>3);
    }
    
    
    
    public static void main (String[] args){
        
        Random random = new Random();
        Scanner scanner =new Scanner(System.in);
        
        System.out.println("/////  BOSS  FIGHT  \\\\\\\\");
        Character selected = choose(stock());
        List<Character> party = stock();
        Character opponent = new Boss("UNDEAD", 300, 50);


        
        /*System.out.println("\nChoose an oppoenent");
        System.out.println("-------------------");
        for (String name : characters.keySet()){
            if (!name.equals(choice1))
                System.out.println(name);
        }
        String choice2 = scanner.nextLine();
        
        Character opponent = characters.get(choice2);
        Character selected = characters.get(choice1);
        */
        System.out.println("\nyou deliver the first strike");
        System.out.println("----------------------------");
        while (selected.isAlive() || opponent.isAlive()){ 
            if (selected.isAlive())
            {   
                for(Character loop : party){
                    if (selected.getName().equals(loop.getName())){
                        loop.setVie(selected.getVie());
                        break;}
                }
                playerTurn(selected,opponent);
            }
            
            else {
                for (Character loop : party){
                    if (loop.getName().equals(selected.getName())) loop.setVie(selected.getVie());
                }
                int i = 0;
                for( Character loop : party){
                    i++;
                    if (loop.getVie() > 0){
                        do{
                            selected = choose(party);
                        }while(selected.getVie() == 0);
                        break;
                    }
                    else if (i == party.size()){
                    System.out.println("*****YOU LOST******");
                    System.out.println("all your fightes are down");
                    return;
                    }
                
                }
            }
        
            
            if (opponent.isAlive()){
                if (random.nextInt(100)>10 && random.nextInt(100)<30 ){
                    opponent.heal();
                }
                else if (random.nextInt(100)< 10 && opponent instanceof SpecialAbility ability)
                        ability.use(selected);
                    else{opponent.attack(selected);}
            }
            
            else{
                System.out.printf("******** CONGRATULATIONS YOU WON ********");
                return;
            }
        }   
    }
}



