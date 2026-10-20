
package etudiant;

import java.util.Random;

interface SpecialAbility {
    void use(Character Target);
}

public class Character {
    Random random = new Random();
    private String name;
    private int vie;
    private int attackDamage;
    
    public Character(String name,int vie,int attackDamage){
        this.name = name;
        this.vie = vie;
        this.attackDamage = attackDamage;
    }
    
    int getVie(){
        return vie; 
    }
    
    void setVie(int Vie){
        vie = Vie;
    }
    
    int getDamage(){
        return attackDamage;
    }
    
    String getName(){
        return name;
    }
    
    void attack (Character Target){
        int ATK = random.nextInt(getDamage());
        System.out.printf("%s(%dhp) attacks %s(%dhp)\n", name, vie, Target.getName(),Target.getVie()); 
        if (ATK > Target.getVie())System.out.println(Target.name+" loses "+ Target.getVie()+"hp \n\n");
        else System.out.println(Target.name+" loses "+ ATK+"hp \n\n");
        Target.vie = Math.max(0, Target.vie - ATK);
       
    }
    
    public boolean isAlive(){
        return vie > 0;
    }
    
    void heal(){
        if (vie == 100 || vie <= 0){
            System.out.printf("%s Cannot heal \n\n",name);
        }
        else{
            int Vie = vie;
            vie = Math.max(0,Math.min(100,vie+20));
            System.out.printf("%d recovered for %s\n\n",vie-Vie, name);}
       
    }
}




