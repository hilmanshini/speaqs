package speaqs.hilmanshini.service;

import android.view.View;
import hilmanshini.speaqs.SpeaqsBoxActivityListener;
import hilmanshini.speaqs.TestActivity;

public interface SpeaqsBoxService extends Service{
	void loadContainerSpeaqsBox();
	void initView();
	void addSample();
	public void bindUpdateStatusButton(SpeaqsBoxActivityListener speaqsBoxActivityListener);
	
	void showDialogUpdateStatus(SpeaqsBoxActivityListener speaqsBoxActivity);
	
	
	
	
	
	
	
	void submitDataUpdateStatusToServer(SpeaqsBoxActivityListener speaqsBoxActivity);
	
	void dismisUpdateStatusDialog(SpeaqsBoxActivityListener speaqsBoxActivity);
	void bindMenuButton(SpeaqsBoxActivityListener testActivity);
        public void showMentionDialog(TestActivity aThis);

    

    public void showSpeaqsDialog(TestActivity aThis);

    public void makeButtonMenuFit();

    public void handleOnBack();


}
