/*
 * Copyright (c) 2013- Facebook.
 * All rights reserved.
 */

package frontend.c;

import static org.hamcrest.MatcherAssert.assertThat;
import static utils.matchers.DotFilesEqual.dotFileEqualTo;

import com.google.common.collect.ImmutableList;

import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import utils.DebuggableTemporaryFolder;
import utils.InferException;
import utils.InferRunner;

public class SwitchStmtTest {

  @Rule
  public DebuggableTemporaryFolder folder = new DebuggableTemporaryFolder();

  @Test
  public void whenCaptureRunSwitchStmtThenDotFilesAreTheSame()
      throws InterruptedException, IOException, InferException {
    String switch_src =
        "infer/tests/codetoanalyze/c/frontend/switchstmt/switch.c";

    String switch_dotty =
        "infer/tests/codetoanalyze/c/frontend/switchstmt/switch.dot";

    ImmutableList<String> inferCmd =
        InferRunner.createCInferCommandFrontend(folder, switch_src);
    File newDotFile = InferRunner.runInferFrontend(inferCmd);
    assertThat(
        "In the capture of " + switch_src +
            " the dotty files should be the same.",
        newDotFile, dotFileEqualTo(switch_dotty));
  }
}
