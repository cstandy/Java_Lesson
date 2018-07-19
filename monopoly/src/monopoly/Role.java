package monopoly;

public class Role {

	/* variables */
	private String name;
	private int point;
	private int money;
	private int position;

	/* methods */

	/**
	  @brief Use the skill with super star.
	  */
	public void useSuperStar() {}

	/**
	  * @brief        Use the ability.
	  * @parame dice  The face of dice thrown.
	  */
	public void useAbility(int dice) {}

	/**
	  * @brief functions called by useAbility()
	  */
	protected void useAbility_1() {}
	protected void useAbility_2() {}
	protected void useAbility_3() {}
	protected void useAbility_4() {}
	protected void useAbility_5() {}
	protected void useAbility_6() {}

}
