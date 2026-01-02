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
            ),
            BookDetail(
                id = 3,
                title = "A Silent Voice 1",
                authors = listOf(
                    Author.dummies[1]
                ),
                publisher = "National Geographic Books",
                publishedDate = "2015-05-26",
                description = "LEARNING TO LISTEN Shoya is a bully. When Shoko, a girl who can’t hear, enters his elementary school class, she becomes their favorite target, and Shoya and his friends goad each other into devising new tortures for her. But the children’s cruelty goes too far. Shoko is forced to leave the school, and Shoya ends up shouldering all the blame. Six years later, the two meet again. Can Shoya make up for his past mistakes, or is it too late? Read the manga industry insiders voted their favorite of 2014! “A very powerful story about being different and the consequences of childhood bullying… Read it.” -Anime News Network “The word heartwarming was made for manga like this.” -Manga Bookshelf",
                isbn10 = "163236056X",
                isbn13 = "9781632360564",
                pageCount = 0,
                categories = listOf(
                    Category.dummies[0]
                ),
                coverUrl = "https://covers.openlibrary.org/b/isbn/9781632360564-L.jpg",
                language = "en"
            ),
            BookDetail(
                id = 4,
                title = "A Silent Voice 2",
                authors = listOf(
                    Author.dummies[1]
                ),
                publisher = "National Geographic Books",
                publishedDate = "2015-07-28",
                description = "SPEAKING WITH ACTIONS It’s been five years since Shoya Ishida bullied Shoko Nishimiya so badly she left their elementary school, because of one simple difference between them: Shoya can hear, and Shoko can’t. In the intervening time, Shoya’s life has changed completely. Shunned by his friends, Shoya’s longed for the chance to make up for his cruelty. When it finally comes, will he find the voice to tell Shoko he’s changed? And will Shoko listen?",
                isbn10 = "1632360578",
                isbn13 = "9781632360571",
                pageCount = 0,
                categories = listOf(
                    Category.dummies[0]
                ),
                coverUrl = "https://covers.openlibrary.org/b/isbn/9781632360571-L.jpg",
                language = "en"
            ),
            BookDetail(
                id = 5,
                title = "A Silent Voice 3",
                authors = listOf(
                    Author.dummies[1]
                ),
                publisher = "National Geographic Books",
                publishedDate = "2015-09-29",
                description = "ARE YOU AFRAID OF HER? Shoya’s decided to do everything he can to make up for how terribly he treated Shoko, his former classmate who can’t hear. But more than the challenge of learning to communicate, it means facing a past he thought he’d left behind forever. Now a reunion with old friends will transform Shoya, and his relationship with Shoko.",
                isbn10 = "1632360586",
                isbn13 = "9781632360588",
                pageCount = 0,
                categories = listOf(
                    Category.dummies[0]
                ),
                coverUrl = "https://covers.openlibrary.org/b/isbn/9781632360588-L.jpg",
                language = "en"
            ),
            BookDetail(
                id = 6,
                title = "A Silent Voice 4",
                authors = listOf(
                    Author.dummies[1]
                ),
                publisher = "National Geographic Books",
                publishedDate = "2015-11-24",
                description = "WORDS OF REASSURANCE Once upon a time, Shoya was terribly cruel to Shoko, his elementary school classmate who couldn’t hear. To make up for his past sins, Shoya has devoted himself to repaying the debt of happiness he owes. So when Shoko faces a romantic setback, Shoya assembles some familiar faces from their past for a trip to the amusement park that may just change things for Shoya, too.",
                isbn10 = "1632360594",
                isbn13 = "9781632360595",
                pageCount = 0,
                categories = listOf(
                    Category.dummies[0]
                ),
                coverUrl = "https://covers.openlibrary.org/b/isbn/9781632360595-L.jpg",
                language = "en"
            ),
            BookDetail(
                id = 7,
                title = "A Silent Voice 5",
                authors = listOf(
                    Author.dummies[1]
                ),
                publisher = "National Geographic Books",
                publishedDate = "2016-01-19",
                description = "A QUIET CALM Despite their tense pasts, Shoya begins to embrace the friend group that used to terrorize Shoko because she couldn’t hear. Now that summer vacation is in full swing, the crew can work together to film Tomohiro’s eccentric movie. Each fun-filled day lazily passes by, but doubt tugs at Shoya’s heavy heart and he is desperate to cling on to meaningful moments before they are gone…",
                isbn10 = "1632360608",
                isbn13 = "9781632360601",
                pageCount = 0,
                categories = listOf(
                    Category.dummies[0]
                ),
                coverUrl = "https://covers.openlibrary.org/b/isbn/9781632360601-L.jpg",
                language = "en"
            ),
            BookDetail(
                id = 8,
                title = "A Silent Voice 6",
                authors = listOf(
                    Author.dummies[1]
                ),
                publisher = "National Geographic Books",
                publishedDate = "2016-04-19",
                description = "A SILENT PRAYER Time stands still for both Shoya and Shoko. Triggered by past traumas, Shoya coldly attacked his friends and burnt the bridges he first set out to rebuild. Shoko feels a deep responsibility for this disaster and attempts to pay for it by taking her own life. Meanwhile, each of their friends finally show their true colors. After everything has fallen apart, how will they mend their hearts and put the pieces back together?",
                isbn10 = "1632360616",
                isbn13 = "9781632360618",
                pageCount = 0,
                categories = listOf(
                    Category.dummies[0]
                ),
                coverUrl = "https://covers.openlibrary.org/b/isbn/9781632360618-L.jpg",
                language = "en"
            ),
            BookDetail(
                id = 9,
                title = "A Silent Voice 7",
                authors = listOf(
                    Author.dummies[1]
                ),
                publisher = "National Geographic Books",
                publishedDate = "2016-05-31",
                description = "SEE YOU LATER Shoya’s life hangs on by a thread after he jumped just in time to save Shoko. Despite the despair, Shoko is determined to move forward and get back what she thinks she has ruined… But broken friendships can heal, too. Quietly, but surely, the disbanded crew finds their spirit — the show must go on! As the movie-making reconvenes, the kids begin to transform the world that had once been so cruel to them. What could the future hold for everyone? Final volume!",
                isbn10 = "1632362228",
                isbn13 = "9781632362223",
                pageCount = 0,
                categories = listOf(
                    Category.dummies[0]
                ),
                coverUrl = "https://covers.openlibrary.org/b/isbn/9781632362223-L.jpg",
                language = "en"
            ),
            BookDetail(
                id = 10,
                title = "Tokyo Ghoul",
                authors = listOf(
                    Author.dummies[2]
                ),
                publisher = "VIZ Media LLC",
                publishedDate = "2015-06-16",
                description = "Ghouls live among us, the same as normal people in every way—except their craving for human flesh. Ken Kaneki is an ordinary college student until a violent encounter turns him into the first half-human half-ghoul hybrid. Trapped between two worlds, he must survive Ghoul turf wars, learn more about Ghoul society and master his new powers. Shy Ken Kaneki is thrilled to go on a date with the beautiful Rize. But it turns out that she’s only interested in his body—eating it, that is. When a morally questionable rescue transforms him into the first half-human half-Ghoul hybrid, Ken is drawn into the dark and violent world of Ghouls, which exists alongside our own.",
                isbn10 = "1421580365",
                isbn13 = "9781421580364",
                pageCount = 224,
                categories = listOf(
                    Category.dummies[0]
                ),
                coverUrl = "https://covers.openlibrary.org/b/isbn/9781421580364-L.jpg",
                language = "en"
            ),
            BookDetail(
                id = 11,
                title = "Tokyo Ghoul, Vol. 2",
                authors = listOf(
                    Author.dummies[2]
                ),
                publisher = "VIZ Media LLC",
                publishedDate = "2015-08-18",
                description = "Ghouls live among us, the same as normal people in every way—except their craving for human flesh. Ghouls live among us, the same as normal people in every way—except their craving for human flesh. Ken Kaneki is an ordinary college student until a violent encounter turns him into the first half-human half-ghoul hybrid. Trapped between two worlds, he must survive Ghoul turf wars, learn more about Ghoul society and master his new powers. Unable to discard his humanity but equally unable to suppress his Ghoul hunger, Ken finds salvation in the kindness of friendly Ghouls who teach him how to pass as human and eat flesh humanely. But recent upheavals in Ghoul society attract the police like wolves to prey, and they don’t discriminate between conscientious and ravenous Ghouls.",
                isbn10 = "1421580373",
                isbn13 = "9781421580371",
                pageCount = 208,
                categories = listOf(
                    Category.dummies[0]
                ),
                coverUrl = "https://covers.openlibrary.org/b/isbn/9781421580371-L.jpg",
                language = "en"
            ),
            BookDetail(
                id = 11,
                title = "Tokyo Ghoul, Vol. 3",
                authors = listOf(
                    Author.dummies[2]
                ),
                publisher = "VIZ Media LLC",
                publishedDate = "2015-10-20",
                description = "Ghouls live among us, the same as normal people in every way—except their craving for human flesh. Ghouls live among us, the same as normal people in every way—except their craving for human flesh. Ken Kaneki is an ordinary college student until a violent encounter turns him into the first half-human half-ghoul hybrid. Trapped between two worlds, he must survive Ghoul turf wars, learn more about Ghoul society and master his new powers. Kaneki is still trying to get used to his new life when Commission of Counter Ghoul agents Mado and Amon start sniffing around for Hinami. Kaneki and Touka are going to have to get them off her tail and fast. No easy task now that Kaneki’s got to bring humans and Ghouls to a rapid truce at the same time.",
                isbn10 = "1421580381",
                isbn13 = "9781421580388",
                pageCount = 0,
                categories = listOf(
                    Category.dummies[0]
                ),
                coverUrl = "https://covers.openlibrary.org/b/isbn/9781421580388-L.jpg",
                language = "en"
            ),
            BookDetail(
                id = 12,
                title = "Tokyo Ghoul",
                authors = listOf(
                    Author.dummies[2]
                ),
                publisher = "VIZ Media LLC",
                publishedDate = "2015-12-15",
                description = "Ghouls live among us, the same as normal people in every way—except their craving for human flesh. Ken Kaneki is an ordinary college student until a violent encounter turns him into the first half-human half-ghoul hybrid. Trapped between two worlds, he must survive Ghoul turf wars, learn more about Ghoul society and master his new powers. Kaneki meets Shu Tsukiyama, known as The Gourmet for his interesting and sadistic tastes. And Kaneki learns more about what happened to Rize and what it means to be a One-Eyed Ghoul.",
                isbn10 = "142158039X",
                isbn13 = "9781421580395",
                pageCount = 192,
                categories = listOf(
                    Category.dummies[0]
                ),
                coverUrl = "https://covers.openlibrary.org/b/isbn/9781421580395-L.jpg",
                language = "en"
            ),
            BookDetail(
                id = 13,
                title = "Tokyo Ghoul",
                authors = listOf(
                    Author.dummies[2]
                ),
                publisher = "VIZ Media LLC",
                publishedDate = "2016-02-16",
                description = "Ghouls live among us, the same as normal people in every way—except their craving for human flesh. Ken Kaneki is an ordinary college student until a violent encounter turns him into the first half-human half-ghoul hybrid. Trapped between two worlds, he must survive Ghoul turf wars, learn more about Ghoul society and master his new powers. Kaneki, Nishio, and Touka struggle to work together to rescue their human friend Kimi while Ghoul Investigator deaths skyrocket in Wards 9 through 12. When reinforcements are called in on both sides, the stakes are suddenly higher than ever.",
                isbn10 = "1421580403",
                isbn13 = "9781421580401",
                pageCount = 200,
                categories = listOf(
                    Category.dummies[0]
                ),
                coverUrl = "https://covers.openlibrary.org/b/isbn/9781421580401-L.jpg",
                language = "en"
            ),
            BookDetail(
                id = 14,
                title = "Tokyo Ghoul",
                authors = listOf(
                    Author.dummies[2]
                ),
                publisher = "VIZ Media LLC",
                publishedDate = "2016-04-19",
                description = "Ghouls live among us, the same as normal people in every way—except their craving for human flesh. Ken Kaneki is an ordinary college student until a violent encounter turns him into the first half-human half-ghoul hybrid. Trapped between two worlds, he must survive Ghoul turf wars, learn more about Ghoul society and master his new powers. Things heat up in the Ward Eleven as the CCG investigates the high rate of Investigator deaths. Kaneki has an explosive run-in with Aogiri Tree, the Ghoul Gang, run by a Ghoul known only as the Sekigan King.",
                isbn10 = "1421580411",
                isbn13 = "9781421580418",
                pageCount = 200,
                categories = listOf(
                    Category.dummies[0]
                ),
                coverUrl = "https://covers.openlibrary.org/b/isbn/9781421580418-L.jpg",
                language = "en"
            )
        )
    }
}
