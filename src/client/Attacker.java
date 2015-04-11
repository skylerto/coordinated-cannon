package client;

/**
 * Interface to allow both Coordinator and Client to implement what an attack
 * is.
 * 
 * @author cse23170 (212166906)
 *
 */
public interface Attacker {

	/**
	 * Implemented by both Coordinator and the Attackers.
	 */
	public void attack();
}
