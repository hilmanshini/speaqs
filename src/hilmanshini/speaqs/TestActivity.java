package hilmanshini.speaqs;

import speaqs.hilmanshini.service.SpeaqsBoxService;
import speaqs.hilmanshini.service.SpeaqsBoxServiceImpl;
import speaqs.hilmanshini.service.ViewFlipperSpeaqsBoxService;
import speaqs.hilmanshini.service.ViewFlipperSpeaqsBoxServiceImpl;
import speaqs.hilmanshini.tool.AndroidTool;
import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class TestActivity extends Activity implements SpeaqsBoxActivityListener {

    SpeaqsBoxService speaqsBoxService = new SpeaqsBoxServiceImpl(this);
    ViewFlipperSpeaqsBoxService boxService = new ViewFlipperSpeaqsBoxServiceImpl(
            this);

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boxService.makeFullScreen();
        boxService.setViewToSpeaqsBoxFlipper();
        boxService.loadViewFlipper();

        //speaqsBoxService.initView();
        speaqsBoxService.bindUpdateStatusButton(this);
        speaqsBoxService.bindMenuButton(this);
        speaqsBoxService.loadContainerSpeaqsBox();
        speaqsBoxService.makeButtonMenuFit();
        speaqsBoxService.makeSearchContainerFit();
        speaqsBoxService.bindPickImageMyProfile(this);
        speaqsBoxService.addSample(this);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onTouchEvent(MotionEvent touchevent) {
        boxService.handleTouch(touchevent);
        return false;
    }

    @Override
    public void onUpdateStatusClick(View v) {
        this.speaqsBoxService.showDialogUpdateStatus(this);
    }

    public void onSubmitUpdateStatusClick(View v) {
        speaqsBoxService.submitDataUpdateStatusToServer(this);
    }

    public void onMentionButtonClick(View v) {
        speaqsBoxService.showMentionDialog(this);
    }

    public void onSpeaqsButtonClick(View v) {
        speaqsBoxService.showSpeaqsDialog(this);
    }

    public void onCancelButtonClick(View v) {
        speaqsBoxService.dismisUpdateStatusDialog(this);
    }

    @Override
    public void OnVoiceBoxButtonClick(View v) {
        boxService.jumpToVoiceBox();

    }

    @Override
    public void onMentionButtonMenuClick(View v) {
        boxService.jumpToMention();

    }

    @Override
    public void onMySpeaqsMenuClick(View v) {
        boxService.jumpToMySpeaqs();
    }

    @Override
    public void onSearchMenuClick(View v) {
        boxService.jumpToSearch(v);
    }

    public void onMentionBackPressed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed(); //To change body of generated methods, choose Tools | Templates.
        System.err.println("netlog x");
        speaqsBoxService.handleOnBack();
    }

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		speaqsBoxService.handleOnActivityResult(requestCode,resultCode,data,this);
	}

	@Override
	public void onFinishPickImageMyProfile() {
		if(speaqsBoxService.isImageBitmapFromGalleryExists()){
			speaqsBoxService.sendImageChangeToServer(this);
		}  
	}
	
	public void onGetResponseImageChangeFromServer(){
		if(speaqsBoxService.isResponseImageChangeServerSuccess()){
			speaqsBoxService.changeImageProfileWithPickedInGallery();
		} else {
			speaqsBoxService.alertSpeaqsDialogResponse(speaqsBoxService.getResponseImageChangeFromServer());
		}
	}

	@Override
	public void onPickImageMyProfileClick() {
		speaqsBoxService.pickGallery();
	}

	@Override
	public void onFinishRecord() {
		speaqsBoxService.makeActiveUpdateStatusMelodiButtonAvailable();
	}


	@Override
	public void onReplyClick(View invoker) {
		this.speaqsBoxService.showDialogUpdateStatus(this);
	}

	@Override
	public void onShareClicK(View v) {
	}

	@Override
	public void onFavClick(View v) {
		if(speaqsBoxService.isAlreadyFavorite()){
			this.speaqsBoxService.cancelFavorite(v,this);
		} else {
			this.speaqsBoxService.submitFavorite(v,this);
		}
		
	}



	@Override
	public void onFavSubmitGetResponse(View v) {
		speaqsBoxService.makeUnFav(v);
		
	}

	@Override
	public void onFavCancelGetResponse(View v) {
		speaqsBoxService.makeFav(v);
		
	}
	

	
}
