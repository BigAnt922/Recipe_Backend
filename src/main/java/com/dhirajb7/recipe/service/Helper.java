package com.dhirajb7.recipe.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class Helper {

	public String changeTrackerOutput(String data) {

		if (data.length() == 0) {
			return "nothing changed";
		} else {
			return data.trim().replaceAll(" ", ", ") + " changed.";
		}

	}

	public List<Long> getChangedIngredent(List<Long> alreadyPresentInDB, List<Long> newlyAddedIds) {

		Set<Long> inDB = new HashSet<Long>(alreadyPresentInDB);

		Set<Long> incoming = new HashSet<Long>(newlyAddedIds);

		Map<Long, Integer> map = new HashMap<Long, Integer>();

		for (Long id : inDB) {
			map.put(id, 1);
		}

		for (Long id : incoming) {
			if (map.containsKey(id)) {
				map.put(id, map.get(id) + 1);
			} else {
				map.put(id, 1);
			}
		}

		return map.entrySet().stream().filter(aa -> aa.getValue() == 1).map(aa -> aa.getKey())
				.collect(Collectors.toList());
	}

	public List<String> getChangedRoles(List<String> alreadyPresentInDB, List<String> newlyAddedRoles) {

		Set<String> inDB = new HashSet<String>(alreadyPresentInDB);

		Set<String> incoming = new HashSet<String>(newlyAddedRoles);

		Map<String, Integer> map = new HashMap<String, Integer>();

		for (String id : inDB) {
			map.put(id, 1);
		}

		for (String id : incoming) {
			if (map.containsKey(id)) {
				map.put(id, map.get(id) + 1);
			} else {
				map.put(id, 1);
			}
		}

		return map.entrySet().stream().filter(aa -> aa.getValue() == 1).map(aa -> aa.getKey())
				.collect(Collectors.toList());
	}

}
