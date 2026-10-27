
package etudiant;

class Monster extends Character implements SpecialAbility{
    void heavyAttack (Character Target){
        Target.setVie(Math.max(0, Target.getVie()-20));
    }
    
    public Monster(String name,int vie,int attackDamage){
        super(name, vie, attackDamage);
    }
    
    @Override
    public void use(Character Target){
        int loss;
        System.out.printf("HEAVY ATTACK! on %s\n",Target.getName());
        if(Target.getVie()<20) {loss = Target.getVie();}
        else {loss = 20;}
        heavyAttack(Target);
        System.out.printf("%s loses %dhp\n\n", Target.getName(),loss);
    }
    
}
