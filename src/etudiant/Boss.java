
package etudiant;


public class Boss extends Character implements SpecialAbility{
    public Boss (String name, int vie, int Damage){
        super (name ,vie, Damage);
    }
    
    public void SuperAttack(Character Target){
        Target.setVie(Math.max(0, Target.getVie() - 70));
    }
    
    @Override 
    public void heal(){
        if (getVie() == 300 || getVie() <= 0){
            System.out.printf("%s Cannot heal \n\n",getName());
        }
        else{
            int Vie = getVie();
            setVie(Math.max(0,Math.min(300,getVie()+20)));
            System.out.printf("%d recovered for %s\n\n",getVie()-Vie, getName());}
       
    }
    
    @Override 
    public void use(Character Target){
        int loss;
        System.out.printf("HEAVY ATTACK! on %s\n",Target.getName());
        if(Target.getVie()<70) {loss = Target.getVie();}
        else {loss = 70;}
        SuperAttack(Target);
        System.out.printf("%s loses %dhp\n\n", Target.getName(),loss);
    }
}
