package util

import java.math.BigInteger
import java.security.MessageDigest

object MathUtils {

    fun md5Hash(str: String): String {
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(str.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }

    fun <String> permute(list:List <String>):List<List<String>>{
        if(list.size==1) return listOf(list)
        val perms=mutableListOf<List <String>>()
        val sub=list[0]
        for(perm in permute(list.drop(1)))
            for (i in 0..perm.size){
                val newPerm=perm.toMutableList()
                newPerm.add(i,sub)
                perms.add(newPerm)
            }
        return perms
    }
}