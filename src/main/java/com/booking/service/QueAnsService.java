package com.booking.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.booking.entity.Answers;
import com.booking.entity.Parent;
import com.booking.entity.Questionnaire;
import com.booking.model.request.AnswerRequest;
import com.booking.model.request.QueAnsRequest;
import com.booking.model.response.QueAnsResponse;

public interface QueAnsService {

	QueAnsResponse findById(Optional<Long> id);

	QueAnsResponse findAllByEntityCode(Optional<String> entityCode, Integer pageNo, Integer fetchSize);

	QueAnsResponse save(QueAnsRequest request);

	QueAnsResponse update(QueAnsRequest request);
	
	QueAnsResponse delete(QueAnsRequest request);
	
	String ID = "ID";
	
	default QueAnsResponse mapParentToGrid(Set<Questionnaire> questions, List<Parent> parents, 
			String category,Long size) {
		if(!CollectionUtils.isEmpty(questions)) {
			List<Questionnaire> questionsSorted = questions.stream().filter(en -> 
			(Objects.nonNull(en.getQuestionCategory()) && en.getQuestionCategory().equalsIgnoreCase(category) 
					&& en.isVisible())).sorted((o1,o2)->o1.getColumnOrderId().compareTo(o2.getColumnOrderId())).toList();
			String[][] grid = new String[parents.size()+1][questionsSorted.size()+1];
			if(!CollectionUtils.isEmpty(questionsSorted)) {
				List<String> header = new ArrayList<>();
				header.add(ID);
				grid[0][0] = ID;
				for(int i=0;i<questionsSorted.size();i++) {
					header.add(questionsSorted.get(i).getQuestionCode());
					grid[0][i+1] = questionsSorted.get(i).getQuestionText();
				}
				int row =1;
				for(Parent parent:parents) {
						grid[row][0] = parent.getId().toString();
					for(Answers ans:parent.getPAnswers()) {
						int col = header.indexOf(ans.getQuestionCode());
						if(col>=0)
							grid[row][col] = ans.getAnswer();
					}
					row++;
				}
				return new QueAnsResponse(grid, "Success", size);
			}
		}
		return new QueAnsResponse(null, "No Records Found", 0l);
	}

}
