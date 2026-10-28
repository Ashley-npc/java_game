
package etudiant;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class Main {
    
    public static List<Character> team(List<Character> fighters){
        Scanner scanner = new Scanner(System.in);
        int i= 0;
        
        System.out.println("Choose your team fighters");
        System.out.println("----------------");
        for (Character loop : fighters){
            i++;
            System.out.println(i+"- "+loop.getName()+" " + loop.getVie()+"hp " + loop.getDamage()+"DMG");
        }
        
        List<Character> Myteam = new ArrayList<>();
        int j=1;
        
        int k;
        
        do{
            int choice1 = scanner.nextInt();
            for (i = 0; i < fighters.size() && j <= 2; i++) {
                if ((i+1 == choice1)){
                    Myteam.add(fighters.get(i));
                    j++;
                    }
                }
        }while(j<=2);   
        System.out.println("Your team");
        System.out.println("---------");
        j=1;
        for (Character loop : Myteam){
            System.out.println(j+"- "+ loop.getName());
            j++;
        }
        return Myteam;    
    }
    
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
        int i =0;
        int j = 1;
        int choice = -1;
        Character selected = null;
        
        do{ 
            
            for (Character perso : party){
                System.out.println(j+"- "+perso.getName()+"  "+ perso.getVie()+"hp  "+ perso.getDamage()+"DMG");
                j++;
            }
            
            
            for (Character perso : party){
                if (perso == party.get(choice)){
                   selected = perso;
                }
                i++;
            }
                
            if (i == party.size()) System.out.println("->Invalid choice");
        }while(i == party.size());
        return selected;
    }
    
    
    public static void playerTurn(Character selected,Character opponent){
        int choice;
        do{
            
            Scanner scanner =new Scanner(System.in);
            System.out.println("Choose your action");
            System.out.println("1. Attack");
            System.out.println("2. Heal");
            System.out.println("3. Heavy attack(usable only once)");
            System.out.println("4. Quit");
        
        
            choice = scanner.nextInt();
            switch (choice){
                case 1 -> {selected.attack(opponent);break;}
                case 2 -> {selected.heal();break;}
                case 3 -> {if(selected instanceof SpecialAbility ability) ability.use(opponent);break;}
                case 4 -> {return;}
            
                default -> {System.out.println("Invalid choice");break;}
            }
            
        }while (0>choice || choice>3);
    }
    
    public static void show(List<Character> Team){
        int i =1;
        for ( Character loop: Team){
            System.out.println(i+"- "+loop.getName()+" " + loop.getVie()+"hp " + loop.getDamage()+"DMG");
            i++;
        }
    }
    
    public static void main (String[] args){
        
        Random random = new Random();
        Scanner scanner =new Scanner(System.in);
        
        //System.out.println("/////  BOSS  FIGHT  \\\\\\");
        //Character selected = choose(stock());
        List<Character> party = stock();
        List<Character> fighters = party;
        List<Character> Myteam = team(fighters);
        List<Character> opponenT= new ArrayList<>();
        
        System.out.println("Your opponents's team: ");
        for(Character loop : Myteam){
            for(Character loop2 : fighters){
                if(loop.getName().equals(loop2.getName()) ){
                    fighters.remove(loop2);
                    break;
                }
            }
        }
        opponenT = fighters;
        for(Character loop : opponenT){
            System.out.println(loop.getName());
        }
        
        
    
        
        System.out.println("\nyou deliver the first strike");
        System.out.println("----------------------------");
        int i = 1;
        int choice1 = -1;
        int j=0;
        int o = 1;
        
        
        do{
            show(Myteam);
            Character opponent =  opponenT.get(random.nextInt(opponenT.size()));
            Character selected = null;
            
            if(i < Myteam.size()) {
                j=0;
                choice1 = scanner.nextInt();
                selected = Myteam.get(choice1-1) ;
            }   
            
            else{
                for(Character loop: Myteam){
                    if (loop.getVie()>0){
                        selected = Myteam.get(j);
                        break;
                    }
                    else if (j++ == Myteam.size()){
                        System.out.println("All your fighters are down");
                        break;
                    }
                    j++;    
                }
            }
            
            if(selected.isAlive()){
                for(Character loop : Myteam){
                    if (selected.getName().equals(loop.getName())){
                        loop.setVie(selected.getVie());
                        break;}
                }
            }
            else{
                System.out.println(selected.getName()+" is down");
                selected.setVie(selected.getVie());
                i++;
                
            }
                
            playerTurn(selected,opponenT.get(random.nextInt(2)));
            
            if(opponent.getVie()<=0){
                
                for(Character loop : opponenT){
                    if(loop.getVie() >= 0){
                        opponent = loop;
                        break;
                    }
                    o++;
                }
                System.out.println("Congrats You Won");
            }
            else{
                int action = random.nextInt(100)+1;
                if (action <= 10 && opponent instanceof SpecialAbility ability) ability.use(Myteam.get(random.nextInt(2)));
                else if (action > 10 && action <= 30) opponent.heal();
                else opponent.attack(Myteam.get(random.nextInt(2)));
            }
            
        }while(j < Myteam.size() || o < opponenT.size());
        
        /*while (selected.isAlive() || opponent.isAlive()){ 
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
                System.out.println("//"+selected.getName()+" died \\");
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
        }*/   
    }
}


