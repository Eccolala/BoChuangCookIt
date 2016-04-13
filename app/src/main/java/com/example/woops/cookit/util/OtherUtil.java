package com.example.woops.cookit.util;


import android.content.SharedPreferences;
import android.os.Environment;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechSynthesizer;

public class OtherUtil {

    //tts类的设置函数
    public static void tts_setParam(SpeechSynthesizer mTts, SharedPreferences mSharedPreferences,String voicerCloud) {
        // 清空参数
        mTts.setParameter(SpeechConstant.PARAMS, null);
        //设置合成

        //设置使用云端引擎
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        //设置发音人
        mTts.setParameter(SpeechConstant.VOICE_NAME, voicerCloud);

        //设置合成语速
        mTts.setParameter(SpeechConstant.SPEED, mSharedPreferences.getString("speed_preference", "50"));
        //设置合成音调
        mTts.setParameter(SpeechConstant.PITCH, mSharedPreferences.getString("pitch_preference", "50"));
        //设置合成音量
        mTts.setParameter(SpeechConstant.VOLUME, mSharedPreferences.getString("volume_preference", "50"));
        //设置播放器音频流类型
        mTts.setParameter(SpeechConstant.STREAM_TYPE, mSharedPreferences.getString("stream_preference", "3"));

        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/tts.wav");
    }
}
