package com.perpushub.bandung.common.model

data class BookDetail(
    val id: Int,
    val title: String,
    val authors: List<Author>,
    val publisher: String,
    val publishedDate: String,
    val description: String,
    val isbn10: String,
    val isbn13: String,
    val pageCount: Int,
    val categories: List<Category>,
    val coverUrl: String,
    val language: String
) {
    companion object {
        val dummies = listOf(
            BookDetail(
                id = 0,
                title = "your name.",
                authors = listOf(
                    Author.dummies[0]
                ),
                publisher = "Yen Press LLC",
                publishedDate = "2017-05-23",
                description = "Mitsuha, a high school girl living in a small town in the mountains, has a dream that she's a boy living in Tokyo. Taki, a high school boy in Tokyo, dreams he's a girl living in a quaint little mountain town. Sharing bodies, relationships, and lives, the two become inextricably interwoven--but are any connections truly inseverable in the grand tapestry of fate?Written by director MAKOTO SHINKAI during the production of the film by the same title, your name. is in turns funny, heartwarming, and heart-wrenching as it follows the struggles of two young people determined to hold on to one another.",
                isbn10 = "031647309X",
                isbn13 = "9780316473095",
                pageCount = 162,
                categories = listOf(
                    Category.dummies[0]
                ),
                coverUrl = "https://covers.openlibrary.org/b/isbn/9780316473095-L.jpg",
                language = "en"
            ),
            BookDetail(
                id = 1,
                title = "Weathering With You",
                authors = listOf(
                    Author.dummies[0]
                ),
                publisher = "Yen On",
                publishedDate = "2019-12-17",
                description = "Longing to escape his island home, a boy named Hodaka runs away during his first summer of high school to find a new life in Tokyo. As rain falls for days on end and Hodaka struggles to adjust, he meets a girl named Hina who holds a mysterious power: With a single prayer, she can part the clouds and bring back the sun. But her power comes at a price, and as the weather spirals further and further out of control, they must choose what future they truly want for themselves. Written concurrently with production of the 2019 film Weathering With You, this novel comes straight from director Makoto Shinkai, the mind behind 2016's hit your name.!",
                isbn10 = "1975399366",
                isbn13 = "9781975399368",
                pageCount = 0,
                categories = listOf(
                    Category.dummies[1]
                ),
                coverUrl = "https://covers.openlibrary.org/b/isbn/9781975399368-L.jpg",
                language = "en"
            ),
            BookDetail(
                id = 2,
                title = "The Garden of Words",
                authors = listOf(
                    Author.dummies[0]
                ),
                publisher = "Vertical Inc",
                publishedDate = "2014-10-28",
                description = "The Garden of Words brings to the manga page all the beauty and mystery of the award-winning film from artful animator Makoto Shinkai. Beloved for the simple grace of its artwork as much as the poetic elegance of its text (adapted by Midori Motohashi), The Garden of Words begins with a chance, rainswept encounter between Takao, a young man who dreams of becoming a shoe designer, and Yukari, an enigmatic woman he finds sitting alone, nursing a beer on a park bench. The spare interaction of these two lonely souls sparks a spiritual transformation for the young man, and perhaps the woman as well. As this intriguing, understated story unfolds, their lives will become further intertwined amid rain, beer, school, and shoe cobbling. Words are not often necessary, but in this case just a few words can make a difference in one's heart.",
                isbn10 = "1939130832",
                isbn13 = "9781939130839",
                pageCount = 0,
                categories = listOf(
                    Category.dummies[0]
                ),
                coverUrl = "https://covers.openlibrary.org/b/isbn/9781939130839-L.jpg",
                language = "en"
            )
        )
    }
}
