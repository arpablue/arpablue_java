/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.xsystemfile;

/**
 *
 * @author Socrates Flores
 */
import java.util.Vector;
import java.io.*;
import java.util.ArrayList;

public class IniFile {
    /**
     * It is the log file object to write the log file.
     */
    protected LogFile mLog;
    protected ArrayList mSections = null;
    protected String mFile = null;
    protected String mError = null;

    public String getError() {
        return mError;
    }

    public void setFile(String file) {
        mFile = file;
        mSections = new ArrayList();
    }

    public boolean open() {
        File fFile = null;
        RandomAccessFile rLoad = null;
        String sLine = null;
        String sSection = "";
        try {
            if (mFile == null) {
                return false;
            }
            fFile = new File(mFile);
            if (!fFile.exists()) {
                return false;
            }
            rLoad = new RandomAccessFile(fFile, "r");
            sSection = addSection("[]");
            do {
                sLine = rLoad.readLine();
                if (sLine != null) {
                    sLine = sLine.trim();
                    if (sLine.indexOf("#") == 0) {
                        continue;
                    }

                    if (sLine.matches("^\\[.*\\].*")) {
                        sSection = addSection(sLine);
                    } else {
                        addField(sSection, sLine);
                    }
                }
            } while (sLine != null);
        } catch (Exception ex) {
            mError = ex.getMessage();
            mLog.error("(IniFile - open)" + mError);
            return false;
        } finally {
            try {
                if (rLoad != null) {
                    rLoad.close();
                }
            } catch (Exception ex2) {
                mLog.error("(IniFile - open): " + ex2.getMessage());
            }
        }
        return true;
    }

    public boolean open(String file) {
        this.setFile(file);
        return open();
    }

    public boolean save(String file) {
        mFile = file;
        return save();
    }

    public boolean save() {
        try {
            RandomAccessFile f = new RandomAccessFile(mFile, "rw");
            Section elem = null;
            for (int i = 0; i < mSections.size(); i++) {
                elem = (Section) mSections.get(i);
                elem.toFile(f);
            }
            f.close();
        } catch (Exception ex) {
            mLog.error("(IniFile - save): " + ex.getMessage());
            return false;
        }
        return true;
    }

    public String addSection(String line) {
        String sSection = null;
        Section section = null;
        if (line != null) {
            line = line.trim();
            if (line.indexOf("[") > -1) {
                line = line.substring(line.indexOf("[") + 1);
            }
            if (line.indexOf("]") > -1) {
                line = line.substring(0, line.indexOf("]"));
            }
            sSection = line;
            for (int i = 0; i < mSections.size(); i++) {
                section = (Section) mSections.get(i);
                if (section.getName().equalsIgnoreCase(sSection)) {
                    break;
                } else {
                    section = null;
                }
            }
        }
        if (section == null) {
            section = new Section(sSection);
        }
        mSections.add(section);
        return sSection;
    }

    public void addField(String section, String line) {
        Section object = null;
        line = line.trim();

        if (line.indexOf("#") > -1) {
            line = line.substring(0, line.indexOf("#") - 1);
        }
        if (line.length() < 1) {
            return;
        }
            
        for (int i = 0; i < mSections.size(); i++) {
            object = (Section) mSections.get(i);
            if (object.getName().equalsIgnoreCase(section)) {
                object.addValue(line);
                return;
            }

        }
    }

    public void addField(String section, String field, String value) {
        if (section == null) {
            section = "";
        } else {
            section = section.trim();
        }
        if (field == null) {
            return;
        } else {
            field = field.trim();
        }
        if (value == null) {
            value = "";
        }
        Section object = null;
        for (int i = 0; i < mSections.size(); i++) {
            object = (Section) mSections.get(i);
            if (object.getName().equalsIgnoreCase(section)) {
                object.addValue(field, value);
                return;
            }

        }
    }

    public IniFile(String file) {
        setFile(file);

    }

    public IniFile() {
        this(null);
    }

    public String[] getSections() {
        String[] res = new String[mSections.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = ((Section) mSections.get(i)).getName();
        }
        return res;

    }

    @Override
    public String toString() {
        String res = "File: " + mFile;
        String car = "\r\n\t";
        for (int i = 0; i < mSections.size(); i++) {
            res = res + car + ((Section) mSections.get(i)).toString();
        }
        return res;
    }

    public String toString(String section) {

        Section sec = getSection(section);
        if (sec != null) {
            return sec.toString();
        }
        return "";

    }

    public Section getSection(int index) {
        if (index < mSections.size()) {
            return ((Section) mSections.get(index));
        }
        return null;
    }

    public Section getSection(String section) {
        Section sec;
        if (section == null) {
            section = "";
        }
        for (int i = 0; i < mSections.size(); i++) {
            sec = ((Section) mSections.get(i));
            
            if (sec.getName().equalsIgnoreCase(section)) {
                return sec;
            }
        }
        return null;

    }

    public Section getSection() {
        return getSection(null);
    }

    public String getField(String section, String field) {
        Section s = getSection(section);
        
        if (s == null) {
            return null;
        }
        return s.getValue(field);
    }

    public int size() {
        return mSections.size();
    }
}
