package practice;

import java.util.*;

public class Twitter {
    Map<Integer, List<int[]>> tweetsByUser;
    Map<Integer, Set<Integer>> followersByUser;
    int timestamp;

    public Twitter() {
        tweetsByUser = new HashMap<>();
        followersByUser = new HashMap<>();
        timestamp = 0;
    }

    public void postTweet(int userId, int tweetId) {
        tweetsByUser.putIfAbsent(userId, new ArrayList<>());
        tweetsByUser.get(userId).add(new int[]{timestamp++, tweetId});
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        if(tweetsByUser.containsKey(userId)) {
            for (int[] tweet : tweetsByUser.get(userId)) {
                minHeap.offer(tweet);
                if (minHeap.size() > 10) {
                    minHeap.poll();
                }
            }
        }

        if(followersByUser.containsKey(userId)){
            for(int followeeId : followersByUser.get(userId)) {
                if(tweetsByUser.containsKey(followeeId)) {
                    for (int[] tweet : tweetsByUser.get(followeeId)) {
                        minHeap.offer(tweet);
                        if (minHeap.size() > 10) {
                            minHeap.poll();
                        }
                    }
                }
            }
        }

        LinkedList<Integer> newsFeed = new LinkedList<>();
        while (!minHeap.isEmpty()) {
            newsFeed.addFirst(minHeap.poll()[1]);
        }
        return newsFeed;
    }

    public void follow(int followerId, int followeeId) {
        followersByUser.putIfAbsent(followerId, new HashSet<>());
        followersByUser.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if(followersByUser.containsKey(followerId)) {
            followersByUser.get(followerId).remove(followeeId);
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
