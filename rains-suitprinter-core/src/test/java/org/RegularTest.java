package org;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String regex = "\\{([^}]*)\\}";
		String input = "{1900-01-01} {2007/08/13} {1900.01.01} {1900 01 01} {1900-01.01} {1900 13 01 1900 02 31}";
		Pattern pattern = Pattern.compile (regex);
		Matcher matcher = pattern.matcher (input);
		while (matcher.find ())
		{
			System.out.println (matcher.group ());
		}

	}

}
