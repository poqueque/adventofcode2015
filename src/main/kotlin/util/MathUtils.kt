package util

import java.math.BigInteger
import java.security.MessageDigest

object MathUtils {

    fun md5Hash(str: String): String {
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(str.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }

    fun <T> permute(list:List <T>):List<List<T>>{
        if(list.size==1) return listOf(list)
        val perms=mutableListOf<List <T>>()
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