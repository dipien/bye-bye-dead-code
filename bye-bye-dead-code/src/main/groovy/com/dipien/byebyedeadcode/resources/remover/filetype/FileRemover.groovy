package com.dipien.byebyedeadcode.resources.remover.filetype

import com.dipien.byebyedeadcode.resources.remover.AbstractRemover
import com.dipien.byebyedeadcode.resources.remover.SearchPattern
import com.dipien.byebyedeadcode.resources.util.ColoredLogger
import com.dipien.byebyedeadcode.resources.util.DirectoryMatcher
import com.dipien.byebyedeadcode.resources.util.ResultsReport


class FileRemover extends AbstractRemover {

    FileRemover(String fileType, String resourceName, SearchPattern.Type type = SearchPattern.Type.DEFAULT) {
        super(fileType, resourceName, type)
    }

    @Override
    void removeEach(File resDirFile) {
        def checkResult = false
        resDirFile.eachDirRecurse { dir ->
            if (DirectoryMatcher.matchLast(dir.path, fileType)) {
                dir.eachFile { f ->
                    checkResult |= removeFileIfNeed(f)
                }
            }
        }

        if (checkResult) {
            ColoredLogger.log "[${fileType}]   Complete to remove files."
        } else {
            ColoredLogger.log "[${fileType}]   No unused ${fileType} files."
        }
    }

    boolean removeFileIfNeed(File file) {
        if (isMatchedExcludeNames(file.path)) {
            ColoredLogger.logYellow "[${fileType}]   Ignore checking ${file.name}"
            return false
        }

        boolean isMatched = checkTargetTextMatches(extractFileName(file))

        if (!isMatched) {
            ResultsReport.addResult("[${fileType}] ${file.name}")
            ColoredLogger.logGreen("[${fileType}]   Remove ${file.name}")
            if (!dryRun) {
                file.delete()
            }
            return true
        } else {
            return false
        }
    }

    private static String extractFileName(File file) {
        return file.name.take(file.name.lastIndexOf('.'))
    }

}