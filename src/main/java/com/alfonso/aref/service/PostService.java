package com.alfonso.aref.service;

import com.alfonso.aref.model.CommentDTO;
import com.alfonso.aref.model.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {

	@Autowired
	ICommentService iCommentService;

	@Autowired
	RestTemplate restTemplate;

	/**
	 *
	 * @return
	 */
	@Override
	public List<PostDTO> findAll(){
		String uri= "https://jsonplaceholder.typicode.com/posts";
		PostDTO[] result =restTemplate.getForObject(uri, PostDTO[].class);
		System.out.println("llegyue");
		return Arrays.asList(result);
	}

	/**
	 *
	 * @param idUser
	 * @return
	 */
	@Override
	public int postsForUser(Long idUser) {
		List<PostDTO> list = findAll();
		list =  list.stream().filter(predicate -> predicate.getUserId().equals(idUser))
				.collect(Collectors.toList());
	return list.size();
	}

	/**
	 *
	 * @param idUser
	 * @return
	 */
	@Override
	public List<PostDTO> postsOfUser(Long idUser) {
		List<PostDTO> list = findAll();
		List<PostDTO> posts = list.stream().filter(post -> post.getUserId().equals(idUser))
							.collect(Collectors.toList());
		return posts;
	}

	/**
	 *
	 * @param keyWord
	 * @return
	 */
	@Override
	public List<PostDTO> findByBody(String keyWord) {
		List<PostDTO> list = findAll();
		List<PostDTO> posts = list.stream().filter(post -> post.getBody().toLowerCase()
							.contains(keyWord.toLowerCase(Locale.ROOT))).collect(Collectors.toList());
		return posts;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public List<PostDTO> mostCommentedPost() {
		Map<Long, Integer> mapIdPostXComments = new TreeMap<>();
		List<CommentDTO> commentList = iCommentService.findAll();
		commentList.stream().forEach(comment -> {
			if (mapIdPostXComments.containsKey(comment.getPostId())) {
				mapIdPostXComments.put(comment.getPostId(), mapIdPostXComments.get(comment.getPostId()) + 1);
			} else {
				mapIdPostXComments.put(comment.getPostId(), 1);
			}
		});

		Map<Long,Integer> sorted= mapIdPostXComments.entrySet()
				.stream().sorted(Map.Entry.<Long,Integer>comparingByValue().reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1,e2) -> e1, LinkedHashMap::new));

		List<PostDTO> postList= findPostsByIds(sorted);

		return postList;
	}

	/**
	 *
	 * @param mapSorted
	 * @return
	 */
	private List<PostDTO> findPostsByIds(Map<Long,Integer> mapSorted) {
		int contador = 0;
		List<Long> listIdsPost= new ArrayList<>();

		for (Map.Entry<Long, Integer> mpp: mapSorted.entrySet()) {
			if (contador != 10 ) {
				listIdsPost.add(mpp.getKey());
				contador++;
			}
		}
		List<PostDTO> finalListPost =findListPostForComments(listIdsPost);

		return finalListPost;
	}

	/**
	 *
	 * @param listIdsPost
	 * @return
	 */
	private List<PostDTO> findListPostForComments(List<Long> listIdsPost){
		List<PostDTO> finalListPost = listIdsPost.stream().map(this::findById).collect(Collectors.toList());
		return finalListPost;
	}

	/**
	 *
	 * @param idPost
	 * @return
	 */
	private PostDTO findById(Long idPost) {
		List<PostDTO> list = findAll();
		PostDTO postDTO = list.stream().filter(post -> post.getId() == idPost).findAny().orElse(null);
		return postDTO;
	}


}


