package exercises

import scala.annotation.tailrec
import scala.collection.immutable.{HashMap, HashSet}

/*
2. overly simplified social network based on maps
Person = String
- add a person to the network
- remove
- friend (mutual)
- unfriend

- number of friends of a given person
- person with most friends
- how may people have no friends
- if there is a social connection between two people (direct or not)
 */

object testSocialNetwork extends App
{
  val n = new SocialNetwork(new HashMap[String,HashSet[String]]())
  println(n)
  val populated = n.addPerson("Brian").addPerson("Mike").addPerson("Joe").addPerson("Frank").addPerson("Jo")
  println(populated)
  println(populated.removePerson("Frank"))
  val friends = populated.friend("Brian", "Mike").friend("Mike", "Jo").friend("Mike", "Frank")
    .friend("", "Joe")
  println(friends)
  println(friends.unfriend("Mike", "Joe"))
  println(friends.mostPopular())
  println(friends.noFriends())
  println(friends.numNoFriends)
  println(friends.isConnection("Brian", "Joe"))
  //println(friends.isConnection("Brian", "Frank"))
  println(friends.socialConnection("Brian", "Joe"))
  println(friends.socialConnection("Brian", "Frank"))
  println(friends.removePerson("Mike"))

}

case class SocialNetwork(network: Map[String,HashSet[String]]) {
  def addPerson(person: String): SocialNetwork = {
    SocialNetwork(network + (person -> HashSet()))
  }

  def removePerson(person: String): SocialNetwork = {
    def removeAux(friends: Set[String], networkAcc: SocialNetwork): SocialNetwork =
      if(friends.isEmpty) networkAcc
      else removeAux(friends.tail, networkAcc.unfriend(person, friends.head))

    SocialNetwork(removeAux(network(person), this).network - person)
  }

  def friend(personA: String, personB: String): SocialNetwork = {
    if(!network.contains(personA) || !network.contains(personB))
      return this
    val personAList = network(personA) + personB
    val personBList = network(personB) + personA
    SocialNetwork(network + (personA -> personAList) + (personB -> personBList))
  }

  def unfriend(personA: String, personB: String): SocialNetwork = {
    if(!network.contains(personA) || !network.contains(personB))
      return this
    val personAList = network(personA) - personB
    val personBList = network(personB) - personA
    SocialNetwork(network + (personA -> personAList) + (personB -> personBList))
  }

  def friendCount(person: String): Int = {
    if(!network.contains(person)) return 0
    network(person).size
  }

  def mostPopular(): String = {
    network
      .maxBy(_._2.size)
      ._1
  }

  def noFriends(): List[String] = {
    network.filter(_._2.isEmpty).map(_._1).toList
  }

  def numNoFriends() = network.count(_._2.isEmpty)

  // my implementation
  def isConnection(personA: String, personB: String): Boolean = {
    def isConnectionHelper(personA: String, personB: String, prior: HashSet[String]): Boolean = {
      val list = network(personA)
      list.foreach(pA => {
        if(pA == personB) return true
        if(!prior.contains(pA) && isConnectionHelper(pA, personB, prior + pA)) return true
      })
      false
    }
    if(!network.contains(personA) || !network.contains(personB)) return false
    isConnectionHelper(personA, personB, new HashSet[String]())
  }

  // class implementation
  def socialConnection(personA: String, personB: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if(discoveredPeople.isEmpty) return false
      else {
        val person = discoveredPeople.head
        if(person == target) return true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(personB, Set(), network(personA) + personA)
  }
}
