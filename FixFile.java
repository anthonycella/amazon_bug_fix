package fix_quicken_loans_file;

public class FixFile {
	public static void main(String[] args) {

		try {
			String quickenLoansFile = QuickenLoansFile.getFileFromFileExplorer();
			String contents = QuickenLoansFile.read(quickenLoansFile);

			String[] dissectedContents = contents.split("<");

			for (int currentIndex = 0; currentIndex < dissectedContents.length; currentIndex++) {
				if (currentIndex != 0) {
					dissectedContents[currentIndex] = "<" + dissectedContents[currentIndex];
				}
			}

			StringBuilder returnTextBuilder = new StringBuilder();

			for (int currentIndex = 0; currentIndex < dissectedContents.length; currentIndex++) {
				returnTextBuilder.append(dissectedContents[currentIndex]);
			}
			
			//-----------------fixing time!----------------------//
			
			String startDate = null;
			
			for (int currentIndex = 0; currentIndex < dissectedContents.length; currentIndex++) {
				String currentString = dissectedContents[currentIndex];
				if (currentString.contains("<DTPOSTED>")) {
					startDate = currentString.substring(10, currentString.length() - 1);
					break;
				}
			}
			
			String endDate = CurrentDate.get();
			
			
			String[] dissectedFixedContents = QuickenLoansFile.fix(dissectedContents, startDate, endDate);
			
			StringBuilder fixedFileTextBuilder = new StringBuilder();
			
			for (int currentIndex = 0; currentIndex < dissectedFixedContents.length; currentIndex++) {
				fixedFileTextBuilder.append(dissectedFixedContents[currentIndex]);
			}
			
			String fixedFileContents = String.valueOf(fixedFileTextBuilder);
			
			String transactionTestFile = (quickenLoansFile);
			QuickenLoansFile.write(transactionTestFile, fixedFileContents);
			System.out.println("Operation successful: file located at " + quickenLoansFile + " updated");
			
			
		} catch (Exception e) {
			System.out.println("Operation cancelled by user or file not found");
		}

		System.exit(0);
	}
}
