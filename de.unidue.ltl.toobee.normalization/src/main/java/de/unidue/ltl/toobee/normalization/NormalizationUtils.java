package de.unidue.ltl.toobee.normalization;

public class NormalizationUtils
{

    public static String replaceTwitterPhenomenons(String input, String replacement)
    {
        /* Email and atmention are sensitive to order of execution */
        String workingCopy = input;
        workingCopy = normalizeUrls(workingCopy, replacement);
        workingCopy = normalizeEmails(workingCopy, replacement);
        workingCopy = normalizeAtMentions(workingCopy, replacement);
        workingCopy = normalizeHashTags(workingCopy, replacement);
        workingCopy = normalizeRetweet(workingCopy, replacement);
        return workingCopy.trim();
    }

    private static String normalizeRetweet(String workingCopy, String replacement) {
    	
		return workingCopy.replaceAll("RT :", replacement);
	}

	public static String normalizeHashTags(String input, String replacement)
    {
        String HASHTAG = "#[a-zA-Z0-9-_]+";
        String normalized = input.replaceAll(HASHTAG, replacement);
        return normalized;
    }

    public static String normalizeEmails(String input, String replacement)
    {
        String PREFIX = "[a-zA-Z0-9-_\\.]+";
        String SUFFIX = "[a-zA-Z0-9-_]+";

        String EMAIL_REGEX = PREFIX + "@" + SUFFIX + "\\." + "[a-zA-Z]+";
        String normalize = input.replaceAll(EMAIL_REGEX, replacement);
        return normalize;
    }

    public static String normalizeAtMentions(String input, String replacement)
    {
        String AT_MENTION_REGEX = "@[a-zA-Z0-9_-]+";
        String normalize = input.replaceAll(AT_MENTION_REGEX, replacement);
        return normalize;
    }

    public static String normalizeUrls(String input, String replacement)
    {
        String URL_CORE_REGEX = "[\\/\\\\.a-zA-Z0-9-_]+";

        String normalized = input.replaceAll("http:" + URL_CORE_REGEX, replacement);
        normalized = normalized.replaceAll("https:" + URL_CORE_REGEX, replacement);
        normalized = normalized.replaceAll("www\\." + URL_CORE_REGEX, replacement);

        return normalized;
    }
}
