package usecase.commentManager;

public class CommentDisplayCleanupManager
{
    public static int commentNewLineCount;

    public static void setCommentNewLineCount(String display)
    {
        int count = 0;
        for (char i : display.toCharArray())
        {
            if (i == '\n')
            {
                count += 1;
            }
        }

        commentNewLineCount = count;
    }

    public static void eraseCurrentThread()
    {
        System.out.print("\u001B[A".repeat(commentNewLineCount + 1));
        String space = " ".repeat(100);
        System.out.println((space + "\n").repeat(commentNewLineCount));
        System.out.print("\u001B[A".repeat(commentNewLineCount + 1));
    }
}
