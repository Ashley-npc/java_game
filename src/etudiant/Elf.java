
package etudiant;

public class Elf extends Character implements SpecialAbility{
    
    public Elf(String name, int vie, int Damage){
        super(name,vie,Damage);
    }
    
    public void heavyAttack(Character Target){
        Target.setVie(Math.max(0,Target.getVie()-25));
    }
    
    @Override
    public void heal(){
        if (getVie() == 100 || getVie() <= 0){
            System.out.printf("%s Cannot heal \n\n",getName());
        }
        else{
            int Vie = getVie();
            setVie(Math.max(0,Math.min(100,getVie() + 35)));
            System.out.printf("%d recovered for %s\n\n",getVie()-Vie, getName());
        }
    }
    
    @Override
    public void use(Character Target){
        int loss;
        System.out.printf("HEAVY ATTACK! on %s\n",Target.getName());
        if(Target.getVie()<25) {loss =Target.getVie();}
        else {loss = 25;}
        heavyAttack(Target);
        System.out.printf("%s loses %dhp\n\n", Target.getName(),loss);
    }
}
