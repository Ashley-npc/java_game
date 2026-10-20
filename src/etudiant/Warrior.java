
package etudiant;

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
        if(Target.getVie()<30) {loss = Target.getVie();}
        else {loss = 30;}
        heavyAttack(Target); 
        System.out.printf("%s loses %dhp\n\n", Target.getName(),loss);
    }
}


