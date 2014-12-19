package net.sourceforge.yanonymous;

import org.junit.Test;
import junit.framework.Assert;

public class TestClass {
@Test
	public void Anonymous_test() {
 
	LocalCommunityObject A = new LocalCommunityObject("Lol");		

	Assert.assertEquals(A.szamvisszaad(), 5);
 
	}
}
