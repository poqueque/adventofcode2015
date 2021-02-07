package days

class Day16 : Day(16) {

    val auntSueFeatures = listOf<Feature>(
        Feature("children",3),
        Feature("cats",7),
        Feature("samoyeds",2),
        Feature("pomeranians",3),
        Feature("akitas",0),
        Feature("vizslas",0),
        Feature("goldfish",5),
        Feature("trees",3),
        Feature("cars",2),
        Feature("perfumes",1),
    )

    override fun partOne(): Any {
        readData()
        sues.forEach { sue ->
            var matches = true
            sue.features.forEach { feature ->
                if (auntSueFeatures.first { asf -> asf.name == feature.name }.amount != feature.amount) matches = false
            }
            if (matches)
                return(sue.number)
        }
        return -1
    }

    override fun partTwo(): Any {
        readData()
        sues.forEach { sue ->
            var matches = true
            sue.features.forEach { feature ->
                val auntAmount =  auntSueFeatures.first { asf -> asf.name == feature.name }.amount
                if ((feature.name == "cats"  || feature.name == "trees") && feature.amount <= auntAmount) matches = false
                if ((feature.name == "pomeranians"  || feature.name == "goldfish") && feature.amount >= auntAmount) matches = false
                if ((feature.name == "children"  || feature.name == "samoyeds" || feature.name == "akitas" || feature.name == "vizslas" || feature.name == "cars" || feature.name == "perfumes") && feature.amount != auntAmount) matches = false
            }
            if (matches)
                return(sue.number)
        }
        return -1
    }

    val sues = mutableListOf<Sue>()

    private fun readData() {
        inputList.forEach {
            val words = it.replace(",","").replace(":","").split(" ")
            var sue = 0
            var features = mutableListOf<Feature>()
            for (i in words.indices step 2) {
                if (i==0) sue = words[1].toInt()
                else features.add(Feature(words[i],words[i+1].toInt()))
            }
            sues.add(Sue(sue,features))
        }
    }

    data class Feature(val name: String, val amount: Int)
    data class Sue(val number: Int, val features: List<Feature>)
}
