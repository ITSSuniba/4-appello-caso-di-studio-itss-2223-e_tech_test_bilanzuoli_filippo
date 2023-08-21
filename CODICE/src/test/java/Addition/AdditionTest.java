package Addition;


import org.junit.jupiter.api.Assertions;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.Report;
import net.jqwik.api.Reporting;
import net.jqwik.api.statistics.Statistics;





class AdditionTest {
	@Provide
    private Arbitrary<Float> values(){
        return Arbitraries.oneOf(
                Arbitraries.floats().between(-50, true, -1, true),
                Arbitraries.floats().between(1, true, 50, true));
    }
	
	@Property
	@Report(Reporting.GENERATED)
	void commutativeProperty(@ForAll("values")float n1,@ForAll("values") float n2) {
		float function = Addition.addition(n1, n2);
		float res = n1 + n2;
		Assertions.assertEquals(function, res);
		Statistics.collect(function);
		
	}
	
	@Property
	@Report(Reporting.GENERATED)
	void associativeProperty(@ForAll("values") float n1, @ForAll("values") float n2, @ForAll("values") float n3) {
		float function = Addition.addition(Addition.addition(n1, n2), n3);
		float res = n1 + n2 + n3;
		Assertions.assertEquals(function, res);
		Statistics.collect(function);
	}
	
	@Property
	@Report(Reporting.GENERATED)
    void neutralProperty(@ForAll ("values") float n1) {
        float n_z = 0;
        float function = Addition.addition(n1, n_z);
        Assertions.assertEquals(function, n1);
        Statistics.collect(function);
    }

    @Property
    @Report(Reporting.GENERATED)
    void additiveInverseProperty(@ForAll ("values") float n1) {
        float n_i = n1 * -1;
        float function = Addition.addition(n1, n_i);
        Assertions.assertEquals(function, 0);
        Statistics.collect(function);
    }
	
}
