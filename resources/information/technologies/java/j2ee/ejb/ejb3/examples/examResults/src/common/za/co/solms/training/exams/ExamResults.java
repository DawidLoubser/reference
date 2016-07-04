package za.co.solms.training.exams;

import javax.ejb.Remote;

/**
 * @author fritz@solms.co.za
 *
 */
@Remote public interface ExamResults
{
  public long add(String studentName, double result);
}
