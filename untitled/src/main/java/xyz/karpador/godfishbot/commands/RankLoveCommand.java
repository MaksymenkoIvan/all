package xyz.karpador.godfishbot.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.telegram.telegrambots.api.objects.Message;

public class RankLoveCommand extends Command {

	private final static String LOVE = "ILOVE";

	private class TestResultList {
		private class TestResult implements Comparable<TestResult> {

			final String fname;
			final String sname;
			final int percentage;

			TestResult(String fname, String sname, int percentage) {
				this.fname = fname;
				this.sname = sname;
				this.percentage = percentage;
			}

			@Override
			public int compareTo(TestResult other) {
				return Integer.compare(other.percentage, percentage);
			}

			@Override
			public String toString() {
				return fname + " and " + sname + " (" + percentage + "%)";
			}
		}

		private final List<TestResult> testResults;

		TestResultList() {
			testResults = new ArrayList<>();
		}

		void addTestResult(String name1, String name2) {
			if (name1.equals(name2)) return;
			for (TestResult tr : testResults) {
				if ((tr.fname.equals(name1) && tr.sname.equals(name2)) ||
					(tr.sname.equals(name1) && tr.fname.equals(name2)))
					return;
			}
			testResults.add(
				new TestResult(
					name1,
					name2,
					getLovePer(
						name1.toUpperCase(),
						name2.toUpperCase()
					)
				)
			);
		}

		String getRankingString() {
			StringBuilder result = new StringBuilder();
			Collections.sort(testResults);
			for (int i = 0; i < testResults.size(); i++) {
				result.append(i + 1).append(". ").append(testResults.get(i));
				if (i != testResults.size() - 1)
					result.append("\n");
			}
			return result.toString();
		}
	}

	private List<String> getCount(String name1, String name2) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < name1.length(); i++) {
			String temp = String.valueOf(name1.charAt(i));

			if (list.contains(temp)) {
				int indexOfElement = list.indexOf(temp);
				int prevCount = Integer.parseInt(list.get(++indexOfElement));
				prevCount++;
				String newCount = String.valueOf(prevCount);
				list.set(indexOfElement, newCount);
				continue;
			}

			list.add(temp);
			list.add("1");
		}

		for (int i = 0; i < name2.length(); i++) {
			String temp = String.valueOf(name2.charAt(i));

			if (list.contains(temp)) {
				int indexOfElement = list.indexOf(temp);
				int prevCount = Integer.parseInt(list.get(++indexOfElement));
				prevCount++;
				String newCount = String.valueOf(prevCount);
				list.set(indexOfElement, newCount);
				continue;
			}

			list.add(temp);
			list.add("1");
		}

		for (int i = 0; i < LOVE.length(); i++) {
			String temp = String.valueOf(LOVE.charAt(i));

			if (list.contains(temp)) {
				int indexOfElement = list.indexOf(temp);
				int prevCount = Integer.parseInt(list.get(++indexOfElement));
				prevCount++;
				String newCount = String.valueOf(prevCount);
				list.set(indexOfElement, newCount);
				continue;
			}

			list.add(temp);
			list.add("1");
		}

		List<String> result = new ArrayList<>();
		for (int i = 1; i < list.size(); i += 2) {
			result.add(list.get(i));
		}
		return result;
	}

	private int getLovePer(String name1, String name2) {
		List<String> count;
		if (name1.compareTo(name2) < 0)
			count = getCount(name1, name2);
		else
			count = getCount(name2, name1);

		if (count.size() == 1) {
			String result = count.get(0);
			return Integer.parseInt(result);
		}

		if (count.size() == 2) {
			String result = count.get(0) + count.get(1);
			return Integer.parseInt(result);
		}

		do {
			List<String> sub = new ArrayList<>();
			int size = count.size() / 2;
			for (int i = 0; i < size; i++) {
				String newC = (Integer.parseInt(count.get(i)) + Integer.parseInt(count.get(count.size() - 1 - i))) + "";

				if (newC.length() == 2) {
					sub.add((newC.charAt(0) + ""));
					sub.add((newC.charAt(1) + ""));
				} else {
					sub.add(newC);
				}
			}

			if ((size * 2) != count.size()) {
				sub.add(count.get(size));
			}

			count = sub;
		} while (count.size() != 2);

		String result = count.get(0) + count.get(1);
		return Integer.parseInt(result);
	}

	@Override
	public String getName() {
		return "ranklove";
	}

	@Override
	public String getUsage() {
		return "/ranklove <name0> <name1>";
	}

	@Override
	public String getDescription() {
		return "В процентах покажет совместимось";
	}

	@Override
	public CommandResult getReply(String params, Message message, String myName) {
		if (params == null) return new CommandResult("Please submit at least two names.");
		String[] names = params.split(" ");
		if (names.length < 2) return new CommandResult("Please submit at least two names.");
		if (names.length == 2) {
			int percentage = getLovePer(names[0].toUpperCase(), names[1].toUpperCase());
			return new CommandResult(names[0] + " and " + names[1] + " fit " + percentage + "%.");
		} else {
			TestResultList list = new TestResultList();
			for (String name1 : names) {
				for (String name2 : names) {
					list.addTestResult(name1, name2);
				}
			}
			return new CommandResult(list.getRankingString());
		}
	}

}