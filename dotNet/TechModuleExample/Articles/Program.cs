using System;

namespace Articles
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] articleTokens = Console.ReadLine().Split(", ");
            Article article = new Article(articleTokens[0], articleTokens[1], articleTokens[2]);
            int commandCount = int.Parse(Console.ReadLine());

            for (int i = 0; i < commandCount; i++)
            {
                string[] commandTokens = Console.ReadLine().Split(": ");
                switch (commandTokens[0])
                {
                    case "Edit":
                        article.Edit(commandTokens[1]);
                        break;
                    case "ChangeAuthor":
                        article.ChangeAuthor(commandTokens[1]);
                        break;
                    case "Rename":
                        article.Rename(commandTokens[1]);
                        break;
                }
            }

            Console.WriteLine(article);
        }
    }

    class Article
    {
        public string Title { get; set; }
        public string Content { get; set; }
        public string Author { get; set; }

        public Article(string title, string content, string author)
        {
            Title = title;
            Content = content;
            Author = author;
        }

        public void Edit(string content)
        {
            Content = content;
        }

        public void ChangeAuthor(string author)
        {
            Author = author;
        }

        public void Rename(string title)
        {
            Title = title;
        }

        public override string ToString()
        {
            return $"{Title} - {Content}: {Author}";
        }
    }
}
