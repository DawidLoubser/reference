package za.co.solms.training.javaee.math;

import javax.ejb.Stateless;

@Stateless
public class DividerImpl implements Divider
{
	@Override
	public double divide(double numerator, double denominator)
			throws DivideByZeroException
	{
		if (denominator == 0)
			throw new DivideByZeroException();
		else
			return numerator / denominator;
	}

}
