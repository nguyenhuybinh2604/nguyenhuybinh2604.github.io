package handle;

import entity.RatingUpdateRequest;

import java.util.List;

public class RatingHandle {
    public int getNextId(List<RatingUpdateRequest> ratingUpdateRequests) {
        int maxId = 0;
        for (RatingUpdateRequest request : ratingUpdateRequests) {
            int id = request.getRequestId();
            if (id > maxId) maxId = id;
        }
        return ++maxId;
    }
}
