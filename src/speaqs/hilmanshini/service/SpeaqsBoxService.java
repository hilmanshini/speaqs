package speaqs.hilmanshini.service;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import hilmanshini.speaqs.SpeaqsBoxActivityListener;


public interface SpeaqsBoxService extends Service{
	void loadContainerSpeaqsBox();
	void initView();
	void addSample(SpeaqsBoxActivityListener vt);
	public void bindUpdateStatusButton(SpeaqsBoxActivityListener speaqsBoxActivityListener);
	
	void showDialogUpdateStatus(SpeaqsBoxActivityListener speaqsBoxActivity);
	
	
	
	
	
	
	
	void submitDataUpdateStatusToServer(SpeaqsBoxActivityListener speaqsBoxActivity);
	
	void dismisUpdateStatusDialog(SpeaqsBoxActivityListener speaqsBoxActivity);
	void bindMenuButton(SpeaqsBoxActivityListener SpeaqsBoxActivityListener);
        public void showMentionDialog(SpeaqsBoxActivityListener aThis);

    

    public void showSpeaqsDialog(SpeaqsBoxActivityListener aThis);

    public void makeButtonMenuFit();

    public void handleOnBack();
	void bindPickImageMyProfile(SpeaqsBoxActivityListener activityListener);
	void pickGallery();
	boolean isPickedGallertMyProfileNull();
	void sendImageChangeToServer(SpeaqsBoxActivityListener activityListener );
	void handleOnActivityResult(int requestCode, int resultCode, Intent data,SpeaqsBoxActivityListener listenerw);
	boolean isImageBitmapFromGalleryExists();
	boolean isResponseImageChangeServerSuccess();
	Object getResponseImageChangeFromServer();
	void alertSpeaqsDialogResponse(Object responseImageChangeFromServer);
	void changeImageProfileWithPickedInGallery();
	void makeSearchContainerFit();
	void makeActiveUpdateStatusMelodiButtonAvailable();
	void submitFavorite(View v,SpeaqsBoxActivityListener xv);
	boolean isAlreadyFavorite();
	void cancelFavorite(View v, SpeaqsBoxActivityListener SpeaqsBoxActivityListener);
	void makeUnFav(View v);
	void makeFav(View v);


}
