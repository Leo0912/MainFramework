package utilities;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class SoftAssert extends Assertion {
	private Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();

	@Override
	protected void doAssert(IAssert<?> a) {
		onBeforeAssert(a);
		try {
			a.doAssert();
			onAssertSuccess(a);
		} catch (AssertionError ex) {
			onAssertFailure(a, ex);
			m_errors.put(ex, a);
		} finally {
			onAfterAssert(a);
		}
	}

	public void addAssert(Map<AssertionError, IAssert<?>> errors) {
		if (!errors.isEmpty()) {
			for (Map.Entry<AssertionError, IAssert<?>> ae : errors.entrySet()) {
				m_errors.put(ae.getKey(), ae.getValue());
			}
		}

	}

	public void assertAll() {
		if (!m_errors.isEmpty()) {
			StringBuilder sb = new StringBuilder("The following asserts failed:");
			boolean first = true;
			for (Map.Entry<AssertionError, IAssert<?>> ae : m_errors.entrySet()) {
				if (first) {
					first = false;
				} else {
					sb.append(",");
				}
				sb.append("\n\t");
				sb.append(ae.getKey().getMessage());
				sb.append("\nStack Trace :");
				sb.append(Arrays.toString(ae.getKey().getStackTrace()).replaceAll(",", "\n"));
			}
			throw new AssertionError(sb.toString());
		}
	}

	public void assertAllFinal() {

		if (!m_errors.isEmpty()) {
			StringBuilder sb = new StringBuilder("The following asserts failed:");
			StringBuilder sb_log = new StringBuilder("The following asserts failed with Stack Trace:");
			try {
				File file = new File("./test-output/log.txt");
				BufferedWriter writer;
				writer = new BufferedWriter(new FileWriter(file));

				boolean first = true;
				int issues = 1;
				for (Map.Entry<AssertionError, IAssert<?>> ae : m_errors.entrySet()) {
					if (first) {
						first = false;
					} else {
						sb.append(".");
					}
					sb.append("\n\t");
					System.getProperty("line.separator");
					sb.append(issues + ") " + ae.getKey().getMessage());
					issues++;
				}
				first = true;
				sb.append("\n\t");
				// sb_log = sb;
				sb_log.append("Errors with Stack Trace:\n\t");
				for (Map.Entry<AssertionError, IAssert<?>> ae : m_errors.entrySet()) {
					if (first) {
						first = false;
						issues = 1;
					} else {
						sb_log.append(",");
					}
					sb_log.append(System.getProperty("line.separator"));
					sb_log.append("\n\t");
					sb_log.append(issues + ") " + ae.getKey().getMessage());
					sb_log.append(System.getProperty("line.separator"));
					sb_log.append("\nStack Trace :");
					sb_log.append(System.getProperty("line.separator"));
					sb_log.append("\t" + Arrays.toString(ae.getKey().getStackTrace()).replaceAll(",", "\n\t"));
					issues++;
				}
				writer.write(sb_log.toString());
				writer.flush();
				writer.close();
				throw new AssertionError(sb.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public Map<AssertionError, IAssert<?>> getAssertError() {
		return m_errors;
	}

}
