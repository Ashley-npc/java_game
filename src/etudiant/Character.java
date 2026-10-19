
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
        Target.vie = Math.max(0, Target.vie - ATK);
        System.out.println(Target.name+" loses "+ ATK+"hp \n\n");
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

class Warrior extends Character implements SpecialAbility{
    void heavyAttack(Character Target){
        Target.setVie(Math.max(0, Target.getVie()-30));
    }
    
     public Warrior(String name,int vie,int attackDamage){
        super(name, vie, attackDamage);
    }
    
    @Override
    public void use (Character Target){
        int loss;
        System.out.printf("HEAVY ATTACK! on %s\n",Target.getName());
        if(Target.getVie()<30) {loss = 100 - (100 - 30);}
        else {loss = 30;}
        heavyAttack(Target); 
        System.out.printf("%s loses %dhp\n\n", Target.getName(),loss);
    }
}

class Mage extends Character implements SpecialAbility{
    void Fireball(Character Target){
        Target.setVie(Math.max(0, Target.getVie()-25));
    }
    
    public Mage(String name,int vie,int attackDamage){
        super(name, vie, attackDamage);
    }
    
    @Override
    public void use(Character Target){
        int loss;
        System.out.printf("HEAVY ATTACK! on %s\n",Target.getName());
        if(Target.getVie()<25) {loss = 100 - (100 - Target.getVie());}
        else {loss = Target.getVie() - 25;}
        Fireball(Target);
        System.out.printf("%s loses %dhp\n\n", Target.getName(),loss);
    }
    
}

class Monster extends Character implements SpecialAbility{
    void ATTACK (Character Target){
        Target.setVie(Math.max(0, Target.getVie()-20));
    }
    
     public Monster(String name,int vie,int attackDamage){
        super(name, vie, attackDamage);
    }
    
    @Override
    public void use(Character Target){
        int loss;
        System.out.printf("HEAVY ATTACK! on %s\n",Target.getName());
        if(Target.getVie()<25) {loss = 100 - (100 - Target.getVie());}
        else {loss = Target.getVie() - 25;}
        ATTACK(Target);
        System.out.printf("%s loses %dhp\n\n", Target.getName(),loss);
    }
    
}